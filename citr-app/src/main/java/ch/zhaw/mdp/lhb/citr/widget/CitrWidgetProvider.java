/**
 *
 */
package ch.zhaw.mdp.lhb.citr.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.com.rest.facade.ClientGroupServicesImpl;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;
import ch.zhaw.mdp.lhb.citr.util.SessionHelper;
import ch.zhaw.mdp.lhb.citr.util.SharedPreferencHelper;

/**
 * @author Daniel Brun
 *
 *         AppWidgetProvider for the Citr-Widget
 */
public class CitrWidgetProvider extends AppWidgetProvider {

    /**
     * tag of the widget class
     */
    private static final String TAG = "WIDGET";

    /**
     * on widget update
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
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

    /**
     * if a widget should be deleted
     * @param context
     * @param appWidgetIds
     */
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
        SessionHelper sessPrefs = new SessionHelper(context);

        if (sessPrefs.getPreferenceDefaultNull(SessionHelper.KEY_USERNAME) != null
                && sessPrefs
                .getPreferenceDefaultNull(SessionHelper.KEY_PASSWORD) != null) {

            int groupId = prefs.getInt(
                    SharedPreferencHelper.SHARED_PREF_WIDGET, "config-"
                    + appWidgetId);

            GroupServices groupServices = new ClientGroupServicesImpl(context);

            ResponseObject<MessageDTO> resp = groupServices
                    .getNewestMessage(groupId);

            if (resp.isSuccessfull()) {
                String citrText = resp.getResponseObject().getMessageText();

                RemoteViews remoteViews = new RemoteViews(
                        context.getPackageName(), R.layout.widget_layout);

                // Set the text
                remoteViews.setTextViewText(R.id.citrText, citrText);

                // Tell the widget manager
                appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
            }
        }

    }

    /**
     * Updates the widget with the given id.
     *
     * @param context The context
     * @param appWidgetManager The widget manager.
     * @param appWidgetId The widget it.
     * @param aCitr The citr to display
     */
    public static void updateAppWidget(Context context,
                                       AppWidgetManager appWidgetManager, int appWidgetId, String aCitr) {

        Log.d(TAG, "updateAppWidget appWidgetId=" + appWidgetId);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        // Set the text
        remoteViews.setTextViewText(R.id.citrText, aCitr);

        // Tell the widget manager
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }
}
