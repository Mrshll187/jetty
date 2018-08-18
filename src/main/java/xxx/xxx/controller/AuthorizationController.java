package xxx.xxx.controller;

import java.util.Date;

import javax.annotation.PostConstruct;

import spark.Spark;
import xxx.xxx.annotation.AutoWired;
import xxx.xxx.annotation.Controller;
import xxx.xxx.service.PropertyService;
import xxx.xxx.util.Notifier;

@Controller
public class AuthorizationController {

	@AutoWired
	private PropertyService propertyService;
	
	@PostConstruct
	public void init() {
		
		Spark.before((requet, response) ->{
		
			Notifier.playSound();
			System.out.println("Incoming Request " + new Date());
		});
		
		Spark.after((request, response) ->{
			
			System.out.println("Outgoing Response " + new Date());
		});
	}
}
