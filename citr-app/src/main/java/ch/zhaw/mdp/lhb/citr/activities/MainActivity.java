package ch.zhaw.mdp.lhb.citr.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

/**
 * @author Michael Hadorn Date: 30.10.13 Time: 21:28
 * 
 *         This is the MainActivity. Here is the main page of our app. You going to find 2 areas (list with users groups and a list with all group where current user is member of).
 */
public class MainActivity extends CitrBaseActivity {

    /**
     * container for all groups where i'm the owner
     */
    private List<GroupDTO> groups;

    /**
     * container for all groups where i'm member of it
     */
    private List<SubscriptionDTO> groupSubscriptions;

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

    private GroupAdapter groupOwnerAdapter;
    private SubscriptionAdapter subscriptionAdapter;

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

	groups = new ArrayList<GroupDTO>();
	groupSubscriptions = new ArrayList<SubscriptionDTO>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
	super.onResume();

	// get groups via rest
	ResponseObject<List<GroupDTO>> respGroups = groupServices
		.getUserGroups();

	if (respGroups.isSuccessfull()) {
	    groups = respGroups.getResponseObject();

	    final ListView lvOwnGroups = (ListView) findViewById(R.id.lvOwnGroups);
	    groupOwnerAdapter = new GroupAdapter(this, groups);
	    lvOwnGroups.setAdapter(groupOwnerAdapter);
	    setIntentOfGroupDetails(lvOwnGroups);
	}

	ResponseObject<List<SubscriptionDTO>> respSubsc = groupServices
		.getUserSubscriptions();

	if (respSubsc.isSuccessfull()) {
	    groupSubscriptions = respSubsc.getResponseObject();

	    // set list with "member of"-groups
	    final ListView lvMemberOfGroups = (ListView) findViewById(R.id.lvMemberOfGroups);
	    subscriptionAdapter = new SubscriptionAdapter(this,
		    groupSubscriptions);
	    lvMemberOfGroups.setAdapter(subscriptionAdapter);
	    setIntentOfGroupDetails(lvMemberOfGroups);
	}
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
	    public void onItemClick(AdapterView<?> parent, final View view,
		    int position, long id) {
		// get clicked group
		GroupDTO group = (GroupDTO) parent.getItemAtPosition(position);

		Log.i("MAIN", "MY Group id: " + group.getId());
		// store selected group
		// sharedPrefs.storeInt(SharedPreferencHelper.SHARED_PREF_CONTEXT_GROUP, "group", group.getId());

		// Return
		Intent intentGroup = new Intent(getApplicationContext(),
			CitrCreateActivity.class);
		intentGroup.putExtra("group", group.getId());
		startActivity(intentGroup);
	    }
	});
    }
}