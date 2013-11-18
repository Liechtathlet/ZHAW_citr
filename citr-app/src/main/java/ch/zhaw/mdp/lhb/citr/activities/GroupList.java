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
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 30.10.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class GroupList extends CitrBaseActivity {

    private static final String TAG = "GroupListActivity";

    private IRGroupServices groupServices;

    // private List groupsResult = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list);

        groupServices = new ClientRGroupServicesImpl(this);

        /*
        // starts: dummy groups
        GroupDTO group1 = new GroupDTO();
        group1.setName("Gruppe 1");
        GroupDTO group2 = new GroupDTO();
        group2.setName("Gruppe 2");
        this.groupsResult = new ArrayList();
        this.groupsResult.add(group1);
        this.groupsResult.add(group2);
        // end: dummy groups
        */


        ResponseObject<List<GroupDTO>> respGroupsAll = groupServices.getAllGroups();

        List groupsResult = respGroupsAll.getResponseObject();
        if (groupsResult.size() > 0) {
            // set list with own groups
            final ListView lvGroupResults = (ListView) findViewById(R.id.lvGroupResult);
            final GroupAdapter adapterOwn = new GroupAdapter(this, groupsResult);
            lvGroupResults.setAdapter(adapterOwn);
        }



    }
}