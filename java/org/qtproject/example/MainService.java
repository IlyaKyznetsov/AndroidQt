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

public class MainService extends QtService {
    private static final String TAG="MainService DF";

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
}
