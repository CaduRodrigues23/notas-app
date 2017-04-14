package org.lema.notasapp.infra.firebase.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.lema.notasapp.R;
import org.lema.notasapp.ui.activity.FeedActivity;

/**
 * Created by leonardocordeiro on 07/04/17.
 */

public class PushService extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseMessaging.getInstance().subscribeToTopic("feed");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        Notification notification = buildWith(title, message);
        show(notification);

    }

    private void show(Notification notification) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private Notification buildWith(String title, String text) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,
                                                new Intent(this, FeedActivity.class),
                                                PendingIntent.FLAG_CANCEL_CURRENT);


        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(text);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.drawable.ic_status_notify_news);

        Notification notification = notificationBuilder.build();

        notification.defaults|= Notification.DEFAULT_SOUND;
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;

        return notification;
    }
}
