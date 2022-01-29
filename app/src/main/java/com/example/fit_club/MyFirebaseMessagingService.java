package com.example.fit_club;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "Mynotifcanal";

    @Override
    public void onMessageReceived( RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String myMessage = remoteMessage.getNotification().getBody();
        Log.d("FirebaseMessage","vous venez de recevoir une notification: "+myMessage);

        // rediriger vers cette activité (ActualitesActivite)
        Intent intent = new Intent(getApplicationContext(),ActualitesActivite.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(this,0, new Intent[]{intent},0);
        //creer une notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CANAL);
        notificationBuilder.setContentTitle("Mes Notifications !");
        notificationBuilder.setContentText(myMessage);
        //ajouter l'action
        //n

        //l'icone de la notification
        notificationBuilder.setSmallIcon(R.drawable.bell);

        //envoyer la notification

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            String channelId = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_desc);
            NotificationChannel channel = new NotificationChannel(channelId, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);
        }
        notificationManager.notify(1,notificationBuilder.build());
    }
}
