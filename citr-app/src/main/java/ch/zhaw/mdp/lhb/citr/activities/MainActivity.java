package ch.zhaw.mdp.lhb.citr.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.adapters.GroupAdapter;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 30.10.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {

    private ArrayList groupsOwn = new ArrayList();
    private ArrayList groupsMemberOf = new ArrayList();


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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


        // set list with own groups
        final ListView lvOwnGroups = (ListView) findViewById(R.id.lvOwnGroups);
        final GroupAdapter adapterOwn = new GroupAdapter(this, this.groupsOwn);
        lvOwnGroups.setAdapter(adapterOwn);
        this.setIntentOfGroupDetails(lvOwnGroups);

        // set list with "member of"-groups
        final ListView lvMemberOfGroups = (ListView) findViewById(R.id.lvMemberOfGroups);
        final GroupAdapter adapterMemberOf = new GroupAdapter(this, this.groupsMemberOf);
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


    public void createGroup(View view) {
        Intent intent = new Intent(this, GroupCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    public void createCitr(View view) {
        Intent intent = new Intent(this, CitrCreateActivity.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

    public void getMemberOf(View view) {
        Intent intent = new Intent(this, GroupList.class);
        // intent.putExtra('', );
        startActivity(intent);
    }

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