package ru.aorr.Launcher;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class LauncherActivity extends Activity {

    static final String TAG = "ru.aorr.Launcher.LauncherActivity";
    private final void logging(String message, boolean isNotification) {
        Log.w(TAG, message);
        if (isNotification) {
            Toast.makeText(getApplicationContext(), TAG + " qt: " + message, Toast.LENGTH_SHORT).show();
        }
    }

    // Messenger for communicating with the service.
    Messenger messengerCoreAgentService = null;
    Messenger messengerDeliveryAgentService = null;
    // Flag indicating whether we have called bind on the service.
    boolean isBoundCoreAgentService;
    boolean isBoundDeliveryAgentService;

    private ServiceConnection coreAgentServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            logging("onServiceConnected("+className.toString()+","+service.toString()+")",true);
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.

            messengerCoreAgentService = new Messenger(service);
            isBoundCoreAgentService = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            logging("onServiceDisconnected("+className.toString()+")",true);
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messengerCoreAgentService = null;
            isBoundCoreAgentService = false;

            servicesBind();
        }
    };
    private ServiceConnection deliveryAgentServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            logging("onServiceConnected("+className.toString()+","+service.toString()+")",true);
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            messengerDeliveryAgentService = new Messenger(service);
            isBoundDeliveryAgentService = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            logging("onServiceDisconnected("+className.toString()+")",true);
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
        Log.i(TAG, "onCreate(Bundle savedInstanceState)");


             Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                 @Override
                 public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                     logging("uncaughtException--=-=-=--=-=-=-=-=-=-=-=-=-=", true);
                     Log.e("qt Alert","Lets See if it Works !!!");
                 }
             });


    }



    private void servicesBind() {
        logging("servicesBind", true);
//        int a=10/0;

        bindService(new Intent(this, CoreAgentService.class), coreAgentServiceConnection,
                Context.BIND_AUTO_CREATE);

        bindService(new Intent(this, DeliveryAgentService.class), deliveryAgentServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    private void servicesUnbind() {
        logging("servicesUnbind", true);
        if (isBoundCoreAgentService) {
            unbindService(coreAgentServiceConnection);
            isBoundCoreAgentService = false;
        }

        if (isBoundDeliveryAgentService) {
            unbindService(deliveryAgentServiceConnection);
            isBoundDeliveryAgentService = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
            super.onDestroy();
            Log.i("Test", "Activity: onDestroy");
            logging("Activity: onDestroy", true);
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
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo serviceInfo : services) {
            String serviceName = serviceInfo.service.getClassName();
            Log.i(TAG, "qt:" + serviceName);
        }
        Log.i(TAG, "qt Get services size:" + services.size());
    }

    public void onServicesBind(View view) {
        servicesBind();
    }

    public void onServicesUnbind(View view) {
        servicesUnbind();
    }

    public void onStartDriverControl(View view) {
        startActivity(new Intent(this, DriverControl.class));
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
