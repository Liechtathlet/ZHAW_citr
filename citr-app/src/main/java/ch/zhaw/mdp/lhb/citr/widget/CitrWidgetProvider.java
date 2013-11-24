/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.widget;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.util.SharedPreferencHelper;

/**
 * @author Daniel Brun
 * 
 *         AppWidgetProvider for the Citr-Widget
 */
public class CitrWidgetProvider extends AppWidgetProvider {

    private static final String TAG = "WIDGET";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	    int[] appWidgetIds) {
	Log.d(TAG, "onUpdate");

	Intent intent = new Intent(context.getApplicationContext(),
		CitrWidgetUpdateService.class);
	intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

	// Update the widgets via the service
	context.startService(intent);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
	Log.d(TAG, "onDeleted");
	SharedPreferencHelper prefs = new SharedPreferencHelper(context);

	// When the user deletes the widget, delete the preference associated with it.
	int N = appWidgetIds.length;
	for (int i = 0; i < N; i++) {
	    prefs.delete(SharedPreferencHelper.SHARED_PREF_WIDGET, "config-"
		    + appWidgetIds[i]);
	}
    }

    /**
     * Updates the widget with the given id.
     * 
     * @param context The context
     * @param appWidgetManager The widget manager.
     * @param appWidgetId The widget it.
     */
    public static void updateAppWidget(Context context,
	    AppWidgetManager appWidgetManager, int appWidgetId) {
	Log.d(TAG, "updateAppWidget appWidgetId=" + appWidgetId);
	SharedPreferencHelper prefs = new SharedPreferencHelper(context);

	String groupIdHash = prefs.getString(
		SharedPreferencHelper.SHARED_PREF_WIDGET, "config-"
			+ appWidgetId);
	
	// TODO: Call Service
	String citrText = groupIdHash + "-" + (new Random().nextInt(100));

	RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
		R.layout.widget_layout);

	// Set the text
	remoteViews.setTextViewText(R.id.citrText, citrText);

	// Tell the widget manager
	appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}
