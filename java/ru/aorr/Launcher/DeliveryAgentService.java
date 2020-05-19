package ru.aorr.Launcher;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.qtproject.qt5.android.bindings.QtService;

public class DeliveryAgentService extends QtService {
    static final String TAG="qt_DeliveryAgentService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind(Intent)");
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind(Intent)");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind(Intent)");
        super.onRebind(intent);
    }

    public class ServiceBinder extends Binder
    {
        public DeliveryAgentService getServiceBinder()
        {
            return DeliveryAgentService.this;
        }
    }
    private final IBinder binder = new ServiceBinder();
}
