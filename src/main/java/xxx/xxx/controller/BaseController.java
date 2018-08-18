package xxx.xxx.controller;

import javax.annotation.PostConstruct;

import spark.Spark;
import xxx.xxx.annotation.AutoWired;
import xxx.xxx.annotation.Controller;
import xxx.xxx.service.DatabaseService;
import xxx.xxx.service.PropertyService;

@Controller
public class BaseController {

	@AutoWired
	private DatabaseService databaseService;
	
	@AutoWired
	private PropertyService propertyService;
	
	@PostConstruct
	public void initRoute() {
				
		Spark.get("/", (req, res) -> {

			return "Started Since : "+propertyService.getStartTime();			
		});
	}
}