/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.activities;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Daniel Brun
 * 
 */
public abstract class CitrBaseActivity extends Activity {

	/**
	 * 
	 */
	public CitrBaseActivity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Clean up the current screen.
	 */
	public void cleanScreen() {

		InputMethodManager inputManager = (InputMethodManager) CitrBaseActivity.this
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		inputManager.hideSoftInputFromWindow(CitrBaseActivity.this
				.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	protected void getDataFromServer(){
		//TODO: Implement
	}
	
	/**
	 * Handles the response of a url call.
	 * 
	 * @param aResult The result string.
	 */
	public void handleResponse(String aResult){
		//Base Implementation - Nothing to do.
	}
}
