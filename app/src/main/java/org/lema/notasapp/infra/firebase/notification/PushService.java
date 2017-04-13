package org.lema.notasapp.infra.firebase.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.lema.notasapp.R;
import org.lema.notasapp.ui.activity.FeedActivity;

/**
 * Created by leonardocordeiro on 07/04/17.
 */

public class PushService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentTitle(remoteMessage.getData().get("title"));

        //trocar pra message (ou de acordo com o que for feito na api)
        notificationBuilder.setContentText(remoteMessage.getData().get("title"));
        notificationBuilder.setSmallIcon(R.drawable.ic_status_notify_news);

        Intent irParaFeed = new Intent(this, FeedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, irParaFeed, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.setContentIntent(pendingIntent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(alarmSound);

        Notification notification = notificationBuilder.build();

        notification.defaults|= Notification.DEFAULT_SOUND;
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);


    }
}
