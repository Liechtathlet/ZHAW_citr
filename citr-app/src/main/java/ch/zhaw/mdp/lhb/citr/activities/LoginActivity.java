package ch.zhaw.mdp.lhb.citr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.Main;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientIRUserServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;

/**
 * @author Daniel Brun
 * 
 *         Activity for the 'Login-Process'.
 */
public class LoginActivity extends CitrBaseActivity {

	private static final String TAG = "LoginActivity";

	public final static String CITR_MAINPAGE = "ch.zhaw.mdp.lhb.citr.Main";

	private IRUserServices userServices;

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		userServices = new ClientIRUserServicesImpl(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(ch.zhaw.mdp.lhb.citr.R.menu.main, menu);

		return true;
	}

	/**
	 * Perform user login
	 * 
	 * @param view
	 *            the view.
	 */
	public void onAEUserLogin(View view) {

		EditText editText = (EditText) findViewById(R.id.loginUserId);

		String openId = editText.getText().toString();

		Log.d(TAG, "Activity-Event: User-Login with: " + openId);

		if (openId != null && !openId.equals("")) {
			UserDTO user = userServices.getUser(openId);

			if (user != null) {
				//TODO: Fix this
				Toast.makeText(getApplicationContext(), "Willkommen " + user.getUsername(), Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(this, Main.class);
				intent.putExtra(CITR_MAINPAGE, openId);
				startActivity(intent);
			}
		}
	}
}
