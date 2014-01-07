package ch.zhaw.mdp.lhb.citr.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.activities.MainActivity;
import ch.zhaw.mdp.lhb.citr.widget.CitrWidgetProvider;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * @author Daniel Brun
 *
 *         Service to handle GCM push notifications.
 */
public class GcmIntentService extends IntentService {

    /**
     * NOTIFICATION_ID
     */
    private static final int NOTIFICATION_ID = 1;

    /**
     * tag of the GcmIntentService
     */
    private static final String TAG = "GcmIntentService";

    /**
     * NotificationManager
     */
    private NotificationManager mNotificationManager;

    /**
     * NotificationCompat Builder
     */
    NotificationCompat.Builder builder;

    /**
     * construct
     */
    public GcmIntentService() {
        super("GcmIntentService");
    }

    /**
     * handle an intent
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) { // has effect of unparcelling Bundle
            // Only get messages
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Log.i(TAG, "Received Push Notification....update widget");

                String group = extras.getString("group");
                String citr = extras.getString("citr");

                // Update widget
                AppWidgetManager appWidgetManager = AppWidgetManager
                        .getInstance(this.getApplicationContext());

                ComponentName thisAppWidget = new ComponentName(
                        getApplicationContext().getPackageName(),
                        CitrWidgetProvider.class.getName());

                int[] allWidgetIds = appWidgetManager
                        .getAppWidgetIds(thisAppWidget);

                for (int widgetId : allWidgetIds) {
                    // Update Widget
                    CitrWidgetProvider.updateAppWidget(this, appWidgetManager,
                            widgetId, citr);
                }

                // Post notification of received message.
                sendNotification(group, citr);
                Log.i(TAG, "Received Citr: " + group + ", " + citr);
            }
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * Displays a notification message.
     *
     * @param aGroup The group
     * @param aCitr The citr
     */
    private void sendNotification(String aGroup, String aCitr) {
        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.citr)
                .setContentTitle("Neues Citr: " + aGroup)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(aCitr))
                .setContentText(aCitr);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}