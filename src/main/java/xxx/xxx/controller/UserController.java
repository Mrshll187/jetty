package xxx.xxx.controller;

import javax.annotation.PostConstruct;

import com.google.gson.JsonArray;
import spark.Spark;
import xxx.xxx.annotation.AutoWired;
import xxx.xxx.annotation.Controller;
import xxx.xxx.model.Payload;
import xxx.xxx.service.DatabaseService;
import xxx.xxx.service.PropertyService;
import xxx.xxx.util.FormatUtil;

@Controller
public class UserController {

	@AutoWired
	private PropertyService propertyService;
	
	@AutoWired
	private DatabaseService databaseService;
	
	@PostConstruct
	public void init() {
		
		Spark.get("/user/:key", (req, res) -> {

			res.type("application/json");
			
			String key = req.params(":key");
			String value = databaseService.get(key);
			
			return FormatUtil.fromObjectToString(new Payload(key, value));			
		});
		
		Spark.post("/user", (req, res) -> {

			String body = req.body();
			Payload payload = FormatUtil.stringToPayload(body);
			
			databaseService.save(payload.getKey(), payload.getValue());
			
			String value = databaseService.get(payload.getKey());
			
			if(value.equals(payload.getValue()))
				return true;
			else
				return false; 			
		});
		
		Spark.put("/user", (req, res) -> {

			String body = req.body();
			Payload payload = FormatUtil.stringToPayload(body);
			
			databaseService.save(payload.getKey(), payload.getValue());
			
			if(databaseService.exists(payload.getKey()))
				return true;
			else
				return false;			
		});
		
		Spark.delete("/user/:key", (req, res) -> {
			
			String key = req.params(":key");
			
			databaseService.delete(key);
			
			if(databaseService.exists(key))
				return false;
			else
				return true;
		});
		
		Spark.get("/list", (req, res) -> {
			
			res.type("application/json");
			
			JsonArray entries = databaseService.list();
			
			return FormatUtil.jsonToString(entries);		
		});
	}
}
