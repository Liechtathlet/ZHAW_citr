package ch.zhaw.mdp.lhb.citr.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.adapters.GroupAdapter;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientRUserServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;
import ch.zhaw.mdp.lhb.citr.util.SessionHelper;

/**
 * @author Michael Hadorn
 * Date: 30.10.13
 * Time: 21:28
 *
 * This is the MainActivity. Here is the main page of our app.
 * You going to find 2 areas (list with users groups and a list with all group where current user is member of).
 */
public class MainActivity extends CitrBaseActivity {

    /**
     * container for all groups where i'm the owner
     */
    private ResponseObject<List<GroupDTO>> groupsOwn;

    /**
     * container for all groups where i'm member of it
     */
    private ResponseObject<List<GroupDTO>> groupsMemberOf;

    /**
     * Service to manage user data via rest
     */
    private IRUserServices userServices;

    /**
     * Service to manage group data via rest
     */
    private IRGroupServices groupServices;

    /**
     * Session Helper to manage request properties via rest
     */
    private SessionHelper preferences;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     *            If the activity is being re-initialized after previously being
     *            shut down then this Bundle contains the data it most recently
     *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     *            is null.</b>
     */
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userServices = new ClientRUserServicesImpl(this);
        preferences = new SessionHelper(this);
        groupServices = new ClientRGroupServicesImpl(this);


        /*
        // starts: dummy groups
        GroupDTO group1 = new GroupDTO();
        group1.setName("Gruppe 1");
        GroupDTO group2 = new GroupDTO();
        group2.setName("Gruppe 2");
        this.groupsOwn = new ArrayList();
        this.groupsOwn.add(group1);
        this.groupsOwn.add(group2);
        this.groupsMemberOf  = this.groupsOwn;
        // end: dummy groups
        */


        // get groups via rest

        this.groupsOwn = groupServices.getOwnedGroup();
        this.groupsMemberOf = groupServices.getUserSubscriptions();


        // load groups via rest
        //Set values to session
        /*
        preferences.setPreference(SessionHelper.KEY_USERNAME, openId);
        //TODO: Remove if OAuth is implemented
        preferences.setPreference(SessionHelper.KEY_PASSWORD, "strongpassword1");
        */

        /*
        ResponseObject<List<GroupDTO>> respGroupsUser = userServices.getGroups();


        ResponseObject<List<GroupDTO>> respGroupsAll = groupServices.getAllGroups();
         */



        // set list with own groups
        final ListView lvOwnGroups = (ListView) findViewById(R.id.lvOwnGroups);
        final GroupAdapter adapterOwn = new GroupAdapter(this, this.groupsOwn.getResponseObject());
        lvOwnGroups.setAdapter(adapterOwn);
        this.setIntentOfGroupDetails(lvOwnGroups);

        // set list with "member of"-groups
        final ListView lvMemberOfGroups = (ListView) findViewById(R.id.lvMemberOfGroups);
        final GroupAdapter adapterMemberOf = new GroupAdapter(this, this.groupsMemberOf.getResponseObject());
        lvMemberOfGroups.setAdapter(adapterMemberOf);
        this.setIntentOfGroupDetails(lvMemberOfGroups);


        /*
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.CITR_MAINPAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message + " hallo");

        // Set the text view as the activity layout
        setContentView(textView);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
*/
        // setContentView(R.layout.main);

       /*
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        */
    }


    /**
     * Create intent to create a new group
     * @param view read android documentation
     */
    public void createGroup(View view) {
        Intent intent = new Intent(this, GroupCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Create intent to create a new citr
     * @param view read android documentation
     */
    public void createCitr(View view) {
        Intent intent = new Intent(this, CitrCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Create intent to get all group to going to be a member
     * @param view read android documentation
     */
    public void getMemberOf(View view) {
        Intent intent = new Intent(this, GroupList.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    /**
     * Set link on group item, who calls the group detail intent
     * @param listview
     */
    public void setIntentOfGroupDetails (ListView listview) {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // final String item = (String) parent.getItemAtPosition(position);


                /*
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
                */
            }
        });
    }
}