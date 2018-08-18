package xxx.xxx.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import xxx.xxx.model.Payload;

public class FormatUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonParser parser = new JsonParser();
	private static Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
	
	public static Payload stringToPayload(String payload) throws Exception {
		return mapper.readValue(payload, Payload.class);
	}
	
	public static String fromObjectToString(Payload payload) throws Exception {
		return mapper.writeValueAsString(payload);
	}
	
	public static JsonObject toJson(String json) {
		return parser.parse(json).getAsJsonObject();
	}
	
	public static String jsonToString(JsonElement json) {
		return gson.toJson(json);
	}
}
