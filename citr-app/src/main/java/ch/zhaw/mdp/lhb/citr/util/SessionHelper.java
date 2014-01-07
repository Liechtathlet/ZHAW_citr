/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Daniel Brun
 *
 * Helper class for the Session-Management (SharedPreferences).
 */
public class SessionHelper {

    /**
     * store the username
     */
	public static final String KEY_USERNAME = "username";
    /**
     * store the password
     */
	public static final String KEY_PASSWORD = "password";

    /**
     * object with preferences in app
     */
    private SharedPreferences settings;

    /**
     * editor object
     */
	private Editor editor;
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anApplicationContext The application context.
	 */
	public SessionHelper(Context anApplicationContext) {
	    settings = anApplicationContext.getSharedPreferences("citr-prefs", 0);
		
		editor = settings.edit();
	}

	/**
	 * Set the setting with the given key and value.
	 * 
	 * @param aKey The key.
	 * @param aValue The value.
	 */
	public void setPreference(String aKey, String aValue){
	    editor.putString(aKey, aValue);
	    editor.commit();
	}
	
	/**
	 * Get the setting with the given key.
	 * 
	 * @param aKey The key.
	 * @return The value to the key or null if the value was not set.
	 */
	public String getPreferenceDefaultNull(String aKey){
	    return settings.getString(aKey,null);
	}
}
