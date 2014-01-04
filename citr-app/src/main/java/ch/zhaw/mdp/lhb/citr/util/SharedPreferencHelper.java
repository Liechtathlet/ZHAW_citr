/**
 *
 */
package ch.zhaw.mdp.lhb.citr.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Daniel Brun
 *
 *         Helper for Shared Preferences
 */
public class SharedPreferencHelper {

    public static final String SHARED_PREF_APP = "ch.zhaw.mdp.lhb.citr.activity.LoginActivity";
    public static final String SHARED_PREF_WIDGET = "ch.zhaw.mdp.lhb.citr.widget.CitrWidgetProvider";
    // public static final String SHARED_PREF_CONTEXT_GROUP = "ch.zhaw.mdp.lhb.citr.widget.CitrContectGroup";

    public static final String REG_ID = "gcm-registration-id";
    public static final String APP_VERSION = "app-version";

    private Context context;

    /**
     * Creates a new instance of this class.
     *
     * @param aContext The context
     */
    public SharedPreferencHelper(Context aContext) {
        context = aContext;
    }

    /**
     * Stores a String Key-Value-Pair in the given shared preferences.
     *
     * @param aPreference The preference
     * @param aKey The key
     * @param aValue The value
     */
    public void storeString(String aPreference, String aKey, String aValue) {
        SharedPreferences prefs = context.getSharedPreferences(aPreference, 0);
        Editor prefEditor = prefs.edit();

        prefEditor.putString(aKey, aValue);
        prefEditor.commit();
    }

    /**
     * Stores a int Key-Value-Pair in the given shared preferences.
     *
     * @param aPreference The preference
     * @param aKey The key
     * @param aValue The value
     */
    public void storeInt(String aPreference, String aKey, int aValue) {
        SharedPreferences prefs = context.getSharedPreferences(aPreference, 0);
        Editor prefEditor = prefs.edit();

        prefEditor.putInt(aKey, aValue);
        prefEditor.commit();
    }

    /**
     * Gets the value of the given key in the given shared preferences.
     *
     * @param aPreference The preferences
     * @param aKey The key.
     * @return The value.
     */
    public String getString(String aPreference, String aKey) {
        SharedPreferences prefs = context.getSharedPreferences(aPreference, 0);

        return prefs.getString(aKey, null);
    }

    /**
     * Gets the value of the given key in the given shared preferences.
     *
     * @param aPreference The preferences
     * @param aKey The key.
     * @return The value.
     */
    public int getInt(String aPreference, String aKey) {
        SharedPreferences prefs = context.getSharedPreferences(aPreference, 0);

        return prefs.getInt(aKey, -1);
    }

    /**
     * Deletes the preference with the given key in the given shared preferences.
     *
     * @param aPreference The preferences.
     * @param aKey The key.
     */
    public void delete(String aPreference, String aKey) {
        SharedPreferences prefs = context.getSharedPreferences(aPreference, 0);
        Editor prefEditor = prefs.edit();
        prefEditor.remove(aKey);
        prefEditor.commit();
    }
}
