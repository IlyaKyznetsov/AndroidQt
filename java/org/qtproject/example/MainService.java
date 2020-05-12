package org.qtproject.example;

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
    public static void startQtAndroidService(Context context) {
            context.startService(new Intent(context, MainService.class));
    }

    private static final String TAG="MainService DF";

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
//          QtApplication.InvokeResult res = QtApplication.invokeDelegate(intent);
//          if (res.invoked)
//              return (IBinder)res.methodReturns;
//          else
//              return null;
        return super.onBind(intent);
      }

      @Override
      public boolean onUnbind(Intent intent)
      {
      	Log.i(TAG, "onUnbind(intent)");
        return super.onUnbind(intent);
      }
}
