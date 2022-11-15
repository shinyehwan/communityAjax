package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ut {
	public static class json {

		private static final ObjectMapper om;

		static{
			om = new ObjectMapper();
		}


		public static String toStr(Object obj, String defaultValue) {

			try {
				return om.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				return defaultValue;
			}
		}

		public static <T> T toObj(String json, Class<T> cls, T defaultValue) {
			try {
				return (T)om.readValue(json, cls);
			} catch (JsonProcessingException e) {
				return defaultValue;
			}
		}
	}
}
