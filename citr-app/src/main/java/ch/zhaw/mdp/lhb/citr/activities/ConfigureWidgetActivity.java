package ch.zhaw.mdp.lhb.citr.activities;

import java.util.List;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.adapters.SubscriptionAdapter;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;
import ch.zhaw.mdp.lhb.citr.util.SessionHelper;
import ch.zhaw.mdp.lhb.citr.util.SharedPreferencHelper;
import ch.zhaw.mdp.lhb.citr.widget.CitrWidgetProvider;

/**
 * @author Daniel Brun
 * 
 * Activity for Widget-Configuration
 */
public class ConfigureWidgetActivity extends CitrBaseActivity {
    /**
     * Tag of Activity
     */
    private static final String TAG = "ConfigureWidgetActivity";

    /**
     * Service to manage group data via rest
     */
    private GroupServices groupServices;

    /**
     * container for all groups where i'm member of it
     */
    private List<SubscriptionDTO> groupsMemberOf;

    private int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private SessionHelper preferences;

    /**
     * Called when the activity is first created.
     * 
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		preferences = new SessionHelper(this);

		Log.i(TAG, "Starting widget configuration");

		// Check if user data is present
		if (preferences.getPreferenceDefaultNull(SessionHelper.KEY_USERNAME) == null
			|| preferences
				.getPreferenceDefaultNull(SessionHelper.KEY_PASSWORD) == null) {
		    AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
		    adBuilder.setCancelable(false); // This blocks the 'BACK' button
		    adBuilder.setMessage(R.string.auth_required);
		    adBuilder.setPositiveButton(R.string.ok,
			    new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				    dialog.dismiss();
				    System.exit(0);
				}
			    });
		    AlertDialog ad = adBuilder.create();
		    ad.show();
		} else {

		    // Load group subscriptions.
		    groupServices = new ClientGroupServicesImpl(this);

		    ResponseObject<List<SubscriptionDTO>> resp = groupServices.getUserSubscriptions();

		    groupsMemberOf = resp.getResponseObject();

		    if (!resp.isSuccessfull() || groupsMemberOf.isEmpty()) {
			Toast.makeText(getApplicationContext(),
				resp.getDisplayMessage(), Toast.LENGTH_LONG).show();
		    }

		    // Set the result to CANCELED. This will cause the widget host to cancel
		    // out of the widget placement if they press the back button.
		    setResult(RESULT_CANCELED);

		    // Set the view layout resource to use.
		    setContentView(R.layout.widget_configure_layout);

		    // Get the list view
		    ListView list = (ListView) findViewById(R.id.lvMyGroupResult);
		    final SubscriptionAdapter adapterMemberOf = new SubscriptionAdapter(this, groupsMemberOf);
		    list.setAdapter(adapterMemberOf);

		    // add listener
		    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> aParent, View aView,
				int aPos, long anId) {
			    SubscriptionDTO sub = (SubscriptionDTO) aParent.getItemAtPosition(aPos);

			    // Store config
			    sharedPrefs.storeInt(SharedPreferencHelper.SHARED_PREF_WIDGET, "config-" + widgetId, sub.getGroup().getId());

			    // Update Widget
			    final Context context = ConfigureWidgetActivity.this;
			    AppWidgetManager appWidgetManager = AppWidgetManager
				    .getInstance(context);

			    CitrWidgetProvider.updateAppWidget(context,
				    appWidgetManager, widgetId);

			    // Return
			    Intent resultValue = new Intent();
			    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			    setResult(RESULT_OK, resultValue);
			    finish();
			}
		    });

		    // Get widget id
		    Intent intent = getIntent();
		    Bundle extras = intent.getExtras();
		    if (extras != null) {
				widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		    }

		    // Fail if no valid id
		    if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
				finish();
		    }
		}
    }
}