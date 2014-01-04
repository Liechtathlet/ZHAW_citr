package ch.zhaw.mdp.lhb.citr.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.adapters.GroupAdapter;
import ch.zhaw.mdp.lhb.citr.adapters.SubscriptionAdapter;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientUserServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;
import ch.zhaw.mdp.lhb.citr.rest.UserServices;
import ch.zhaw.mdp.lhb.citr.util.SessionHelper;

import java.util.List;

/**
 * @author Michael Hadorn Date: 30.10.13 Time: 21:28
 *
 *         This is the MainActivity. Here is the main page of our app. You going to find 2 areas (list with users groups and a list with all group where current user is member of).
 */
public class MainActivity extends CitrBaseActivity {

    /**
     * container for all groups where i'm the owner
     */
    private ResponseObject<List<GroupDTO>> groupsOwn;

    /**
     * container for all groups where i'm member of it
     */
    private ResponseObject<List<SubscriptionDTO>> groupsMemberOf;

    /**
     * Service to manage user data via rest
     */
    private UserServices userServices;

    /**
     * Service to manage group data via rest
     */
    private GroupServices groupServices;

    /**
     * Session Helper to manage request properties via rest
     */
    private SessionHelper preferences;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userServices = new ClientUserServicesImpl(this);
        preferences = new SessionHelper(this);
        groupServices = new ClientGroupServicesImpl(this);

        // get groups via rest
        groupsOwn = groupServices.getUserGroups();
        groupsMemberOf = groupServices.getUserSubscriptions();

        // set list with own groups
        final ListView lvOwnGroups = (ListView) findViewById(R.id.lvOwnGroups);
        final GroupAdapter adapterOwn = new GroupAdapter(this, groupsOwn.getResponseObject());
        lvOwnGroups.setAdapter(adapterOwn);
        setIntentOfGroupDetails(lvOwnGroups);

        // set list with "member of"-groups
        final ListView lvMemberOfGroups = (ListView) findViewById(R.id.lvMemberOfGroups);
        final SubscriptionAdapter adapterMemberOf = new SubscriptionAdapter(this, groupsMemberOf.getResponseObject());
        lvMemberOfGroups.setAdapter(adapterMemberOf);
        setIntentOfGroupDetails(lvMemberOfGroups);

    }

    /**
     * Create intent to create a new group
     *
     * @param view read android documentation
     */
    public void createGroup(View view) {
        Intent intent = new Intent(this, GroupCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Create intent to create a new citr
     *
     * @param view read android documentation
     */
    public void createCitr(View view) {
        Intent intent = new Intent(this, CitrCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Create intent to get all group to going to be a member
     *
     * @param view read android documentation
     */
    public void getMemberOf(View view) {
        Intent intent = new Intent(this, GroupList.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Set link on group item, who calls the group detail intent
     *
     * @param listview
     */
    public void setIntentOfGroupDetails(ListView listview) {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // get clicked group
                GroupDTO group = (GroupDTO) parent.getItemAtPosition(position);

                // store selected group
                // sharedPrefs.storeInt(SharedPreferencHelper.SHARED_PREF_CONTEXT_GROUP, "group", group.getId());

                // Return
                Intent resultValue = new Intent();
                resultValue.putExtra("group", group.getId());
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });

        /*
        int groupId = 0;

        // Get widget id
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            groupId = extras.getInt("group", 0);
        }

        // Fail if no valid id
        if (groupId == 0) {
            finish();
        }


        // load group details
        Intent intentGroup = new Intent(this, CitrCreateActivity.class);
        intentGroup.putExtra("group", groupId);
        startActivity(intentGroup);
        */

    }
}