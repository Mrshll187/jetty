package xxx.xxx.service;

import org.rocksdb.RocksDBException;

import com.google.gson.JsonArray;

public interface Database {

	public void delete(String key) throws RocksDBException;

	public String get(String key) throws RocksDBException;
	
	public boolean exists(String key) throws RocksDBException;

	public void save(String key, String value) throws RocksDBException;
	
	public void update(String key, String value) throws RocksDBException;

	public JsonArray list();
}
