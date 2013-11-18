package ch.zhaw.mdp.lhb.citr.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import ch.zhaw.mdp.lhb.citr.R;

/**
 * @author Daniel Brun
 * 
 * Activity for the 'Error-Handling'.
 */
public class ErrorActivity extends CitrBaseActivity {

	private static final String TAG = "ErrorActivity";

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_error);

	    Log.e(TAG, "Unhandeled Error occurred...displaying error page");
	    
        TextView errorView = (TextView) findViewById(R.id.error);
        errorView.setText(getIntent().getStringExtra("error"));
	}
}
