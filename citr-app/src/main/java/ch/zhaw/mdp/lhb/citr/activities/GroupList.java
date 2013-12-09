package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.adapters.GroupAdapter;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Hadorn
 * Date: 30.10.13
 * Time: 22:58
 *
 * "Mitglied werden"-Button Action. List of all groups.
 */
public class GroupList extends CitrBaseActivity {

    /**
     * Tag of Activity
     */
    private static final String TAG = "GroupListActivity";

    /**
     * Service to manage group data via rest
     */
    private IRGroupServices groupServices;

    // private List groupsResult = new ArrayList();

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     *            If the activity is being re-initialized after previously being
     *            shut down then this Bundle contains the data it most recently
     *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     *            is null.</b>
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list);

        // get groups via rest
        groupServices = new ClientRGroupServicesImpl(this);

        ResponseObject<List<GroupDTO>> respGroupsAll = groupServices.getAllGroups();
        // ResponseObject<List<SubscriptionDTO>> userSubs = groupServices.getUserSubscriptions();

        // match state to each group

        List groupsResult = respGroupsAll.getResponseObject();
        if (groupsResult.size() > 0) {
            // set list with own groups
            final ListView lvGroupResults = (ListView) findViewById(R.id.lvGroupResult);
            final GroupAdapter adapterOwn = new GroupAdapter(this, groupsResult);
            lvGroupResults.setAdapter(adapterOwn);
        }



    }
}