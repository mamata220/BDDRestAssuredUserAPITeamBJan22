package com.lms.api.stepdef.skillmap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseClass {
	FileInputStream  fis;
	public Properties LoadProperties() {
		try {
		//InputStream inStream = getClass().getClassLoader().getResourceAsStream("user.properties");
		fis = new FileInputStream("src/test/resources/properties/user.properties");
		Properties prop = new Properties();
		prop.load(fis);
		fis.close();
			return prop;
			
		} catch (Exception e) {
			System.out.println("Config.properties file not found");
			return null;
		}
	
	}	
}