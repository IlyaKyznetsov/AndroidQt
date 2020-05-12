package org.qtproject.example;

import org.qtproject.qt5.android.bindings.*;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html
import org.qtproject.qt5.android.bindings.QtService;
// Java Service
//import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;
import android.app.PendingIntent;

import android.os.Build;

public class MainService extends QtService {
    private static final String TAG="MainService DF";

    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 5454;

    public static void startQtService(Context context) {
        Log.i(TAG, "about to start");
        context.startService(new Intent(context, MainService.class));
    }

    public static void stopQtService(Context context) {
        Log.i(TAG, "about to stop");
        context.stopService(new Intent(context, MainService.class));
    }

      @Override
      public void onCreate() {
          super.onCreate();
          Log.i(TAG, "Creating Service");
      }

      @Override
      public void onDestroy() {
          super.onDestroy();
          Log.i(TAG, "Destroying Service");
      }

      @Override
      public int onStartCommand(Intent intent, int flags, int startId) {
          int ret = super.onStartCommand(intent, flags, startId);

          // Do some work
          Log.i(TAG, "onStartCommand(Intent intent, int flags, int startId)");


                  synchronized (this) {
                      try {
                          wait(2000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
                  String text = intent.getStringExtra(EXTRA_MESSAGE);
                  showText(text);


          return ret;
      }

      public class ServiceBinder extends Binder
      {
          MainService getServiceBinder()
          {
              return MainService.this;
          }
      }
      private final IBinder binder = new ServiceBinder();

      @Override
      public IBinder onBind(Intent intent)
      {
       Log.i(TAG, "onBind(intent)");
       QtApplication.InvokeResult res = QtApplication.invokeDelegate(intent);
       Log.i(TAG,Boolean.toString(res.invoked));

        return super.onBind(intent);
      }

      @Override
      public boolean onUnbind(Intent intent)
      {
      	Log.i(TAG, "onUnbind(intent)");
        return super.onUnbind(intent);
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
          Intent actionIntent = new Intent(this, MainActivity.class);
          PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
          builder.setContentIntent(actionPendingIntent);

          // Выдача уведомления
          NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
          notificationManager.notify(NOTIFICATION_ID, builder.build());

      }

}
