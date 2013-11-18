package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRMessageServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;

/**
 * @author Michael Hadorn
 * Date: 30.10.13
 * Time: 22:58
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

		messageServices = new ClientRMessageServicesImpl(this);
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

			ResponseObject<Boolean> resp = messageServices
					.createMessage(message);

			if (resp.isSuccessfull()) {
				editText.setText("");
			}

			// TODO: Print error message differently
			Toast.makeText(getApplicationContext(), resp.getDisplayMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}
}