package ch.zhaw.mdp.lhb.citr.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientUserServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.UserServices;
import ch.zhaw.mdp.lhb.citr.util.SessionHelper;
import ch.zhaw.mdp.lhb.citr.util.SharedPreferencHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * @author Daniel Brun
 *
 *         Activity for the 'Login-Process'.
 */
public class LoginActivity extends CitrBaseActivity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private final static String SENDER_ID = "965864818743";

    /**
     * Tag of activity
     */
    private static final String TAG = "LoginActivity";

    /**
     * Service to manage user data via rest
     */
    private UserServices userServices;

    /**
     * Session Helper to manage request properties via rest
     */
    private SessionHelper preferences;
    private SharedPreferencHelper sharedPrefs;
    private GoogleCloudMessaging gcm;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (!checkPlayServices()) {
            Toast.makeText(getApplicationContext(),
                    "Bitte registrieren Sie sich für Google-Play-Services",
                    Toast.LENGTH_SHORT).show();
        }

        userServices = new ClientUserServicesImpl(this);
        preferences = new SessionHelper(this);
        sharedPrefs = new SharedPreferencHelper(this);

        gcm = GoogleCloudMessaging.getInstance(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    /**
     * Create the option in the menu bar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(ch.zhaw.mdp.lhb.citr.R.menu.main, menu);

        return true;
    }

    /**
     * Perform user login
     *
     * @param view the view
     */
    public void onAEUserLogin(View view) {

        if (checkPlayServices()) {

            String regid = getRegistrationId(getApplicationContext());

            if (regid.isEmpty()) {
                registerInBackground();
            } else {
                performLoginOperation(regid);
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Bitte registrieren Sie sich für Google-Play-Services",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Performs the login operation.
     *
     * @param aRegid The registration id.
     */
    private void performLoginOperation(String aRegid) {

        EditText editText = (EditText) findViewById(R.id.loginUserId);

        String openId = editText.getText().toString();

        // Set values to session
        preferences.setPreference(SessionHelper.KEY_USERNAME, openId);
        // TODO: Remove if OAuth is implemented
        preferences
                .setPreference(SessionHelper.KEY_PASSWORD, "strongpassword1");

        Log.d(TAG, "Activity-Event: User-Login with: " + openId);

        if (openId != null && !openId.equals("")) {
            Log.i(TAG, "Try login the ucrrent user");
            ResponseObject<UserDTO> resp = userServices.loginUser(openId,aRegid);

            if (resp.isSuccessfull()) {
        	Log.i(TAG, "Login was successfull");
                UserDTO user = resp.getResponseObject();

                Toast.makeText(getApplicationContext(),
                        resp.getDisplayMessage(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(CITR_MAINPAGE, openId);
                startActivity(intent);
            } else {
        	Log.e(TAG, "Login not successfull...:" + resp.getDisplayMessage());
                // TODO: Show error text
                Toast.makeText(getApplicationContext(),
                        resp.getDisplayMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If it doesn't, display a dialog that allows users to download the APK from the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the current registration ID for application on GCM service.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing registration ID.
     */
    private String getRegistrationId(Context context) {
        String registrationId = sharedPrefs.getString(
                SharedPreferencHelper.SHARED_PREF_APP,
                SharedPreferencHelper.REG_ID);
        if (registrationId == null || registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = sharedPrefs.getInt(
                SharedPreferencHelper.SHARED_PREF_APP,
                SharedPreferencHelper.APP_VERSION);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private void updateData(String aRegid) {
        sharedPrefs.storeInt(SharedPreferencHelper.SHARED_PREF_APP,
                SharedPreferencHelper.APP_VERSION, getAppVersion(this));
        sharedPrefs.storeString(SharedPreferencHelper.SHARED_PREF_APP,
                SharedPreferencHelper.REG_ID, aRegid);

    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and app versionCode in the application's shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask() {
            @Override
            protected String doInBackground(Object... params) {
                String msg = "";
                try {
                    Log.i(TAG, "Send registration request");
                    String regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    Log.i(TAG, msg);

                    updateData(regid);

                    performLoginOperation(regid);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

        }.execute(null, null, null);
    }
}
