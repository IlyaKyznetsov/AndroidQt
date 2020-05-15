package org.qtproject.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.app.ActivityManager;
import android.content.Context;
import android.app.ActivityManager.RunningServiceInfo;
import java.util.List;

import org.qtproject.qt5.android.bindings.QtService;
import org.qtproject.qt5.android.bindings.QtActivity;

import android.util.Log;

public class LauncherActivity extends AppCompatActivity {

    static final String TAG="qtLauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Intent intentCoreAgentService = new Intent(this, CoreAgentService.class);
        bindService(intentCoreAgentService, connectionCoreAgentService, Context.BIND_AUTO_CREATE);

        Intent intentDeliveryAgentService = new Intent(this, DeliveryAgentService.class);
        bindService(intentDeliveryAgentService, connectionDeliveryAgentService, Context.BIND_AUTO_CREATE);
    }

    // ----------------------------------------------------------------------------
    private CoreAgentService coreAgentService;
    private DeliveryAgentService deliveryAgentService;
    private boolean coreAgentServiceBound = false;
    private boolean deliveryAgentServiceBound = false;

    // Связывание с службой
    private ServiceConnection connectionCoreAgentService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            // Код, выполняемый при связи со службой
            Log.i(TAG,"connectionCoreAgentService onServiceConnected(ComponentName componentName, IBinder iBinder)");
            CoreAgentService.ServiceBinder binder = (CoreAgentService.ServiceBinder)service;
            coreAgentService = binder.getServiceBinder();
            coreAgentServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // Код, выполняемый при разрыве связи со службой
            Log.i(TAG,"connectionCoreAgentService onServiceDisconnected(ComponentName componentName)");
            coreAgentServiceBound = false;
        }
    };

    private ServiceConnection connectionDeliveryAgentService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"connectionDeliveryAgentService onServiceConnected(ComponentName componentName, IBinder iBinder)");
            DeliveryAgentService.ServiceBinder binder = (DeliveryAgentService.ServiceBinder)service;
            deliveryAgentService = binder.getServiceBinder();
            deliveryAgentServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"connectionDeliveryAgentService onServiceDisconnected(ComponentName componentName)");
            deliveryAgentServiceBound = false;
        }
    };
    // ----------------------------------------------------------------------------


//    public void onBindService(View view)
//    {
//        Log.i(TAG,"onBindService(View view)");
//        Intent intent = new Intent(this, MainService.class);
//        bindService(intent, connection, Context.BIND_AUTO_CREATE);
//    }
//
//    public void onUnBindService(View view)
//    {
//        Log.i(TAG,"onUnBindService(View view)");
//        if(bound)
//        {
//            unbindService(connection);
//            bound = false;
//        }
//    }

//    public void onStartQtService(View view) {
//        Log.i(TAG,"onStartQtService");
//        Intent intent = new Intent(this, QtService.class);
//        startService(intent);
//    }

//    public void onStopQtService(View view) {
//        Log.i(TAG,"onStopQtService");
//        Intent intent = new Intent(this, QtService.class);
//        stopService(intent);
//    }

    public void onStartDeliveryAgent(View view) {
        Log.i(TAG,"onStartDeliveryAgent");
        Intent intent = new Intent(this, DeliveryAgentService.class);
        startService(intent);
    }

    public void onStopDeliveryAgent(View view) {
        Log.i(TAG,"onStopDeliveryAgent");
        Intent intent = new Intent(this, DeliveryAgentService.class);
        stopService(intent);
    }

    public void onStartCoreAgent(View view) {
        Log.i(TAG,"onStartCoreAgent");
        Intent intent = new Intent(this, CoreAgentService.class);
        startService(intent);
    }

    public void onStopCoreAgent(View view) {
        Log.i(TAG,"onStopCoreAgent");
        Intent intent = new Intent(this, CoreAgentService.class);
        stopService(intent);
    }

    public void onStartDriverControl(View view) {
        Log.i(TAG,"onStartDriverControl");
        Intent intent = new Intent(this, QtActivity.class);
        startActivity(intent);
    }

    public void onStartApart(View view) {
        Log.i(TAG,"onStartApart");
        Intent intent = new Intent(this, ApartService.class);
        startService(intent);
    }

    public void onStopApart(View view) {
        Log.i(TAG,"onStopApartService");
        Intent intent = new Intent(this, ApartService.class);
        stopService(intent);
    }

    public void onServices(View view) {
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo serviceInfo : services) {
            String serviceName = serviceInfo.service.getClassName();
            Log.i(TAG,"qt:"+serviceName);
         }
        Log.i(TAG,"qt Get services size:"+services.size());
    }
}
