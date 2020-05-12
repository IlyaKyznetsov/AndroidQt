package org.qtproject.example;

//import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import android.os.IBinder;
import android.util.Log;

public class DelayedMessageService extends Service {
    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 5453;

    private static final String TAG = "DealyedMessageService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("DF", "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DF", "onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);

        Log.d("DF", "onStartCommand(Intent intent, int flags, int startId)");
        synchronized (this) {
            try {
                wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);

        return result;
    }

    private void showText(final String text) {
        Log.v(TAG, "text: " + text);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        String CHANNEL_ID = new String();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";//getString(R.string.channel_name);
            String description = "channel_description";//getString(R.string.channel_description);
            CHANNEL_ID = "channel_id";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        // Создание построителя уведомлений
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("My notification")
                .setContentText(text)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .addAction(acceptAction)
//                .addAction(declineAction)
                ;

//        Notification builder = new Notification.Builder(this)
//                .setContentTitle("New mail from ")
//                .setContentText(text)
//                .build();


        // Создание отложенного intent
        Intent actionIntent = new Intent(this, LauncherActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        // Выдача уведомления
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}
