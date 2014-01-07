/**
 *
 */
package ch.zhaw.mdp.lhb.citr.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Daniel Brun
 *
 *         Update-Service for the Widgets.
 */
public class CitrWidgetUpdateService extends Service {

    /**
     * tag of the widget service
     */
    private static final String TAG = "CitrWidgetUpdateService";

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
     */
    @Override
    public int onStartCommand(Intent aIntent, int aFlags, int aStartId) {
        Log.i(TAG, "Called Widget update");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        int[] allWidgetIds = aIntent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        for (int widgetId : allWidgetIds) {
            // Update Widget
            CitrWidgetProvider.updateAppWidget(this, appWidgetManager, widgetId);
        }

        stopSelf();

        return super.onStartCommand(aIntent, aFlags, aStartId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent aArg0) {
        return null;
    }

}
