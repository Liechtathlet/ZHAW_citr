package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 30.10.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class GroupCreateActivity extends CitrBaseActivity {

    private static final String TAG = "GroupCreateActivity";

    private IRGroupServices groupServices;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_create);

        groupServices = new ClientRGroupServicesImpl(this);
    }

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