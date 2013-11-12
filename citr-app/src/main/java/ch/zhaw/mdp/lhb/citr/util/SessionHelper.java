/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.util;

import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @author Daniel Brun
 *
 * Helper class for the Session-Management (SharedPreferences).
 */
public class SessionHelper {

	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	
	private SharedPreferences settings;
	
	private Editor editor;
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anApplicationContext The application context.
	 */
	public SessionHelper(Context anApplicationContext) {
		settings = PreferenceManager.getDefaultSharedPreferences(anApplicationContext);
		
		editor = settings.edit();
	}

	public void loginUser(UserDTO aUser){
		
	}
}
