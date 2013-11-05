package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientIRMessageServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;

/**
 * Created with IntelliJ IDEA. User: michael Date: 30.10.13 Time: 22:58
 * 
 * Activity to create a new citr.
 */
public class CitrCreateActivity extends CitrBaseActivity {

	private static final String TAG = "CitrCreateActivity";

	private IRMessageServices messageServices;

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
		setContentView(R.layout.citr_create);
		
		messageServices = new ClientIRMessageServicesImpl(this);
	}

	/**
	 * Perform user login
	 * 
	 * @param view
	 *            the view.
	 */
	public void onAECreateCitr(View view) {

		EditText editText = (EditText) findViewById(R.id.etNewCitr);

		String msgStr = editText.getText().toString();

		Log.d(TAG, "Activity-Event: Create citr with: " + msgStr);

		if (msgStr != null && !msgStr.equals("")) {
			MessageDTO message = new MessageDTO();
			message.setMessageText(msgStr);

			boolean result = messageServices.sendMessage(message);
			String resultMsg = "Das citr konnte nicht übermittelt werden.";

			if (result) {
				resultMsg = " Das citr wurde erfolgreich übermittelt.";
				editText.setText("");
			}

			Toast.makeText(getApplicationContext(),resultMsg, Toast.LENGTH_SHORT)
					.show();
		}
	}
}