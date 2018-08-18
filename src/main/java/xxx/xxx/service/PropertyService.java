package xxx.xxx.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import javax.annotation.PostConstruct;

import xxx.xxx.annotation.Service;
import xxx.xxx.management.LifeCycleManager;

@Service
public class PropertyService implements PropertyLoader {

	private Date startTime = new Date();
	
	private Properties properties = new Properties();
	
	@PostConstruct
	public void init() {
		
		try {			
			properties.load(getClass().getResource("/system.properties").openStream());
		} 
		catch (IOException e) {
			LifeCycleManager.shutDown(e.getMessage(), Level.SEVERE);
		}
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public synchronized String getProperty(String key){
		return properties.getProperty(key);
	}
}
