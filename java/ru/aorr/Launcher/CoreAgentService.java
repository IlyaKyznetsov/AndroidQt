package ru.aorr.Launcher;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import org.qtproject.qt5.android.bindings.QtService;

public class CoreAgentService extends QtService {
    static final String TAG="ru.aorr.Launcher.CoreAgentService";
    final Messenger messenger = new Messenger(new IncomingHandler());
    private final void logging(String message, boolean isNotification)
    {
        Log.w(TAG,message);
        if(isNotification) {
            Toast.makeText(getApplicationContext(), TAG+" : "+message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        logging("onBind("+intent.toString()+")", true);
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        logging("onUnbind("+intent.toString()+")", false);
        return super.onUnbind(intent);
    }

    // -------------------------------------------------------------------------
    /** Обработчик сообщений отправленных сервису */
    //static final int MSG_SAY_HELLO = 1;
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            /*
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
            */
            super.handleMessage(msg);
        }
    }
}