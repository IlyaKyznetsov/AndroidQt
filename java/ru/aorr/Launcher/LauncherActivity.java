package ru.aorr.Launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.app.ActivityManager;

import java.util.List;


public class LauncherActivity extends Activity {

    static final String TAG="qt_LauncherActivity";
    /** Messenger for communicating with the service. */
    Messenger messengerServiceMessengerService = null;
    Messenger messengerCoreAgentService = null;
    Messenger messengerDeliveryAgentService = null;
    /** Flag indicating whether we have called bind on the service. */
    boolean isBoundMessengerService;
    boolean isBoundCoreAgentService;
    boolean isBoundDeliveryAgentService;

    private ServiceConnection messengerServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            messengerServiceMessengerService = new Messenger(service);
            isBoundMessengerService = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messengerServiceMessengerService = null;
            isBoundMessengerService = false;
        }
    };

    private ServiceConnection coreAgentServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            messengerCoreAgentService = new Messenger(service);
            isBoundCoreAgentService = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messengerCoreAgentService = null;
            isBoundCoreAgentService = false;
        }
    };

    private ServiceConnection deliveryAgentServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            messengerDeliveryAgentService = new Messenger(service);
            isBoundDeliveryAgentService = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messengerDeliveryAgentService = null;
            isBoundDeliveryAgentService = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Log.i(TAG,"onCreate(Bundle savedInstanceState)");
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, MessengerService.class), messengerServiceConnection,
                Context.BIND_AUTO_CREATE);

        bindService(new Intent(this, CoreAgentService.class), coreAgentServiceConnection,
                Context.BIND_AUTO_CREATE);

        bindService(new Intent(this, DeliveryAgentService.class), deliveryAgentServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (isBoundMessengerService) {
            unbindService(messengerServiceConnection);
            isBoundMessengerService = false;
        }

        if (isBoundCoreAgentService) {
            unbindService(coreAgentServiceConnection);
            isBoundCoreAgentService = false;
        }

        if (isBoundDeliveryAgentService) {
            unbindService(deliveryAgentServiceConnection);
            isBoundDeliveryAgentService = false;
        }
    }

//    public void sayHello(View v) {
//        if (!isBoundMessengerService) return;
//        // Create and send a message to the service, using a supported 'what' value
//        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
//        try {
//            messengerServiceMessengerService.send(msg);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    // ----------------------------------------------------------------------------
    public void onServices(View view) {
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo serviceInfo : services) {
            String serviceName = serviceInfo.service.getClassName();
            Log.i(TAG,"qt:"+serviceName);
         }
        Log.i(TAG,"qt Get services size:"+services.size());
    }

    public void onStartDriverControl(View view) {
//        Intent intent = new Intent(this, DriverControl.class);
//        Intent intent = new Intent(this, ActivityMessenger.class);
//            intent.putExtra("START",true);
//        startActivity(intent);
    }



    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
