package com.lms.api.stepdef.user;
import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	FileInputStream  fis;
	public Properties loadProperties() {
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
