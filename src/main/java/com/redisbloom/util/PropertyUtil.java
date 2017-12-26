package com.redisbloom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private static final String propertyName = "common.properties";
	private static Properties res;
	
	static{
		res = new Properties(); 
		InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyName); 
		try {
			res.load(in);
			in.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static String getValue(String key){
		return res.getProperty(key);
	}
	public static String getValue(String key,String defaultValue){
		return res.getProperty(key, defaultValue);
	}
}
