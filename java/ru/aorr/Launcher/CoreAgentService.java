package ru.aorr.Launcher;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import org.qtproject.qt5.android.bindings.QtService;

public class CoreAgentService extends QtService {
    static final String TAG="qt_CoreAgentService";

    /** Command to the service to display a message */
    static final int MSG_SAY_HELLO = 1000;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new CoreAgentService.IncomingHandler());

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder = super.onBind(intent);
        Toast.makeText(getApplicationContext(), "CoreAgent.onBind", Toast.LENGTH_SHORT).show();
        //return mMessenger.getBinder();
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        Toast.makeText(getApplicationContext(), "CoreAgent.onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        Toast.makeText(getApplicationContext(), "CoreAgent.onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind(Intent)");
        boolean res = super.onUnbind(intent);
        Toast.makeText(getApplicationContext(), "CoreAgent.onUnbind", Toast.LENGTH_SHORT).show();
        return res;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind(Intent)");
        super.onRebind(intent);
    }
}
