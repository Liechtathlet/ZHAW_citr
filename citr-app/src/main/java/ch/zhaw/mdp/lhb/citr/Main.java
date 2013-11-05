package ch.zhaw.mdp.lhb.citr;

import ch.zhaw.mdp.lhb.citr.activities.CitrCreateActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ch.zhaw.mdp.lhb.citr.activities.GroupCreateActivity;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 30.10.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

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
}