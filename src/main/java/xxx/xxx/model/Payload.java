package xxx.xxx.model;

public class Payload {

	private String key;
	private String value;
	
	public Payload() {}
	
	public Payload(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Payload [key=" + key + ", value=" + value + "]";
	}
}
