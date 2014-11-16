package Util;

import java.util.HashMap;

public class Vault {

	private static HashMap<String, Object> vault = new HashMap<String, Object>();
	
	public static void store(String key, Object value){
		vault.put(key, value);
	}
	
	public static Object get(String key){
		return vault.get(key);
	}
	
}
