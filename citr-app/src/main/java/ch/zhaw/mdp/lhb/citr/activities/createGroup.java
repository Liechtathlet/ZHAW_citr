package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 30.10.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class CitrCreateActivity extends CitrBaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_create);
		
    }

    public void onAECreateGroup(View view) {

    }
    //  http://localhost:8080/citrServer/groups/create/{name:string}/{state:int}/{mode:int}

}