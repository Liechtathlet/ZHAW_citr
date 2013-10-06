package ch.zhaw.mdp.lhb.citr;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class HelloAndroidActivity extends Activity {

	private static final String SERVICE_URL = "http://10.0.2.2:8080/citrServer/myresources/";

	private static final String TAG = "AndroidRESTClientActivity";

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
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(ch.zhaw.mdp.lhb.citr.R.menu.main, menu);
		
		String sampleURL = SERVICE_URL + "test";

		WebServiceTask wst = new WebServiceTask(this,WebServiceTask.GET_TASK, this,
				"GETting data...");

		wst.execute(new String[] { sampleURL });
		return true;
	}

	public void retrieveSampleData(View vw) {
		String sampleURL = SERVICE_URL + "/test";

		WebServiceTask wst = new WebServiceTask(this,WebServiceTask.GET_TASK, this,
				"GETting data...");

		wst.execute(new String[] { sampleURL });
	}

	public void handleResponse(String response) {

		try {
			EditText textF = (EditText) findViewById(R.id.text);
			System.out.println(response);
			JSONObject jso = new JSONObject(response);

			String textStr = jso.getString("text");
			textF.setText("Testing: " + textStr);

		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}

	}

	public void hideKeyboard() {

		InputMethodManager inputManager = (InputMethodManager) HelloAndroidActivity.this
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		inputManager.hideSoftInputFromWindow(HelloAndroidActivity.this
				.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
