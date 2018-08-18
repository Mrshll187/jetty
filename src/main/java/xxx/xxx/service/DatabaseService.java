package xxx.xxx.service;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import xxx.xxx.annotation.Intercept;
import xxx.xxx.annotation.Property;
import xxx.xxx.annotation.Service;
import xxx.xxx.interceptor.TimerInterceptor;
import xxx.xxx.management.LifeCycleManager;

@Service
public class DatabaseService implements Database {

	private Logger logger = Logger.getLogger(DatabaseService.class.getName());
	
	@Property("database.path")
	private String dbPath;
	
	private static RocksDB db = null;

	@PostConstruct
	public void init() throws RocksDBException {
		
		try {

			RocksDB.loadLibrary();

			@SuppressWarnings("resource")
			Options options = new Options().setCreateIfMissing(true);
			File directory = new File(dbPath);

			if (!directory.exists())
				directory.mkdirs();

			db = RocksDB.open(options, dbPath);
			
			logger.info("Using database : " + dbPath);
		} 
		catch (Exception e) {
			
			LifeCycleManager.shutDown("Failure initializing database : " + e.getMessage(), Level.SEVERE);
		}
	}
	
	public synchronized void delete(String key) throws RocksDBException {
		db.delete(key.getBytes());
	}

	@Intercept(attribute="key", interceptor=TimerInterceptor.class)
	public synchronized String get(String key) throws RocksDBException {
		return new String(db.get(key.getBytes()));
	}
	
	public boolean exists(String key) throws RocksDBException {
		return db.get(key.getBytes()) != null;
	}

	public synchronized void save(String key, String value) throws RocksDBException {
		db.put(key.getBytes(), value.getBytes());
	}
	
	public synchronized void update(String key, String value) throws RocksDBException {
		db.put(key.getBytes(), value.getBytes());
	}

	public synchronized JsonArray list() {

		JsonArray entries = new JsonArray();
		RocksIterator itr = db.newIterator();

		for (itr.seekToFirst(); itr.isValid(); itr.next()) {

			String key = new String(itr.key());
			String value = new String(itr.value());

			JsonObject json = new JsonObject();
			json.addProperty(key, value);

			entries.add(json);
		}
		return entries;
	}
	
	public synchronized void shutDown() {
		db.close();
	}
}
