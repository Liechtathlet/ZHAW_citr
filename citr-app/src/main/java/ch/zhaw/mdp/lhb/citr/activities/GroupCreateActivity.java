package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;

/**
 * @author Michael Hadorn
 * Date: 30.10.13
 * Time: 22:58
 *
 * The screen where you can create an group.
 */
public class GroupCreateActivity extends CitrBaseActivity {

    /**
     * Tag of Activity
     */
    private static final String TAG = "GroupCreateActivity";

    /**
     * Service to manage group data via rest
     */
    private GroupServices groupServices;

    /**
     * Runs at the start of intent
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_create);

        groupServices = new ClientGroupServicesImpl(this);
    }

    /**
     * Will be run, if the user wan't create an new group
     * @param view
     */
    public void onAECreateGroup(View view) {

        EditText editText = (EditText) findViewById(R.id.etGroupName);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgMode);

        String msgStr = editText.getText().toString();
        int radButtonId = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radButtonId);
        int idx = radioGroup.indexOfChild(radioButton);

        Log.d(TAG, "Activity-Event: Create group with mode: " + idx);

        boolean isPublicGroup = false;
        if (idx == 1) {
            isPublicGroup = true;
        }

        if (msgStr != null && !msgStr.equals("")) {
            GroupDTO group = new GroupDTO();
            group.setName(msgStr);
            group.setPublicGroup(isPublicGroup);

            ResponseObject<Boolean> resp = groupServices.createGroup(group);

            if(resp.isSuccessfull()){
            	 editText.setText("");
            }

            //TODO: Show error text
            
            Toast.makeText(getApplicationContext(), resp.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}