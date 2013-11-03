/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Daniel Brun
 *
 * Helper to read the Property-File
 */
public class PropertyHelper {

	private static final String PROPERTY_NAME = "app.properties";
	private static Properties props = null;
	
	public static PropertyHelper propertyHelper = new PropertyHelper();
	
	/**
	 * Creates a new instance of this class
	 */
	private PropertyHelper() {
		props=new Properties();
		InputStream inputStream = 
		this.getClass().getClassLoader().getResourceAsStream(PROPERTY_NAME);
		
		try {
			props.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Singleton-Pattern
	 * 
	 * @return the Property helper.
	 */
	public static PropertyHelper getInstance(){
		return propertyHelper;
	}
	
	/**
	 * Gets the value by key.
	 * 
	 * @param aKey the key to load.
	 * @return the corresponding value.
	 */
	public static String get(String aKey){
		return props.getProperty(aKey);
	}
}
