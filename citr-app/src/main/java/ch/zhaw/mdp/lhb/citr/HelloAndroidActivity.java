package ch.zhaw.mdp.lhb.citr;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import ch.zhaw.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloAndroidActivity extends CitrBaseActivity {

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

        this.retrieveSampleData();

		return true;
	}

	public void retrieveSampleData() {
		String sampleURL = SERVICE_URL + "test";

		RESTBackgroundTask rest = new RESTBackgroundTask(this, RESTBackgroundTask.HTTP_GET_TASK);
		
	}

	public void handleResponse(String response) {

		try {
			EditText textF = (EditText) findViewById(R.id.text);
			System.out.println(response);

			ObjectMapper mapper = new ObjectMapper();
			List<UserDTO> user = mapper.readValue(response, new TypeReference<List<UserDTO>>(){});

//			Map<String, String> map = new HashMap<String, String>();
//			ObjectMapper mapper = new ObjectMapper();
//
//			try {
//
//				// convert JSON string to Map
//				map = mapper.readValue(response,
//						new TypeReference<HashMap<String, String>>() {
//						});
//
//				System.out.println(map);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			textF.setText("Hi " + user.get(0).getName());

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
