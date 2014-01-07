package ch.zhaw.mdp.lhb.citr.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientMessageServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;
import ch.zhaw.mdp.lhb.citr.rest.MessageServices;

/**
 * @author Michael Hadorn Date: 30.10.13 Time: 22:58
 * 
 *         Activity to create a new citr.
 */
public class CitrCreateActivity extends CitrBaseActivity {

    /**
     * Tag of Activity
     */
    private static final String TAG = "CitrCreateActivity";

    /**
     * Service to manage message data via rest
     */
    private MessageServices messageServices;

    /**
     * Service to manage group data via rest
     */
    private GroupServices groupServices;

    /**
     * current loaded group id
     */
    private int groupId;

    /**
     * Called when the activity is first created.
     * 
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.citr_create);

	messageServices = new ClientMessageServicesImpl(this);
	groupServices = new ClientGroupServicesImpl(this);

	// check if there a group to prefill
	Intent intent = getIntent();
	groupId = intent.getIntExtra("group", 0);
	if (groupId == 0) {
	    finish();
	}
    }

    /**
     * create a citr
     * 
     * @param view the view.
     */
    public void onAECreateCitr(View view) {

	EditText editText = (EditText) findViewById(R.id.etNewCitr);

	String msgStr = editText.getText().toString();

	Log.d(TAG, "Activity-Event: Create citr with: " + msgStr);

	if (msgStr != null && !msgStr.equals("")) {
	    MessageDTO message = new MessageDTO();
	    message.setMessageText(msgStr);
	    message.setGroupId(groupId);
	    
	    ResponseObject<Boolean> resp = messageServices
		    .createMessage(message);

	    if (resp.isSuccessfull()) {
		editText.setText("");
	    }

	    Toast.makeText(getApplicationContext(), resp.getDisplayMessage(),
		    Toast.LENGTH_SHORT).show();
	}
    }

    /**
     * Delete an own group
     * 
     * @param view the view.
     */
    public void onAEDeleteGroup(View view) {
	// create dialog
	new AlertDialog.Builder(this)
		.setTitle("Gruppe löschen")
		.setMessage(
			"Gruppe wirklich löschen? (Dies betrifft auch alle Zitate.)")
		.setPositiveButton(android.R.string.yes,
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				// continue with delete

				ResponseObject<Boolean> deleteGroup = groupServices
					.deleteGroup(groupId);
				Toast.makeText(getApplicationContext(),
					deleteGroup.getDisplayMessage(),
					Toast.LENGTH_SHORT).show();

				// leave screen
				Intent resultValue = new Intent();
				setResult(RESULT_OK, resultValue);
				finish();

				/*
				 * Intent intentMain = new Intent(getApplicationContext(), MainActivity.class); startActivity(intentMain);
				 */

			    }
			})
		.setNegativeButton(android.R.string.no,
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				// do nothing
			    }
			}).show();
    }

}