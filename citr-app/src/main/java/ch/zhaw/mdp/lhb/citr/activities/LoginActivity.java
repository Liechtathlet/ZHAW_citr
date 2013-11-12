package ch.zhaw.mdp.lhb.citr.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.Main;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRUserServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;

/**
 * @author Daniel Brun
 * 
 *         Activity for the 'Login-Process'.
 */
public class LoginActivity extends CitrBaseActivity {

	private static final String TAG = "LoginActivity";

	private IRUserServices userServices;
	private SharedPreferences preferences;

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

		userServices = new ClientRUserServicesImpl(this);
		preferences = this.getSharedPreferences("ch.zhaw.mdp.lhb.citr",
				Context.MODE_PRIVATE);
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
			ResponseObject<UserDTO> resp = userServices.loginUser(openId);

			if (resp.isSuccessfull()) {
				UserDTO user = resp.getResponseObject();
				
				Toast.makeText(getApplicationContext(),
						resp.getDisplayMessage(), Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(this, Main.class);
				intent.putExtra(CITR_MAINPAGE, openId);
				startActivity(intent);
			} else {
				//TODO: Show error text
				Toast.makeText(getApplicationContext(),
						resp.getDisplayMessage(), Toast.LENGTH_SHORT)
						.show();
			}

		}
	}
}
