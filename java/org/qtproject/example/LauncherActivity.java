package org.qtproject.example;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import org.qtproject.qt5.android.bindings.QtActivity;

import java.util.List;


public class LauncherActivity extends Activity {

    static final String TAG="qt_LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Log.i(TAG,"onCreate(Bundle savedInstanceState)");

        //onBindAgents();

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
            if(binder!=null) {
                coreAgentService = binder.getServiceBinder();
                coreAgentServiceBound = true;
            }
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
            if(binder!=null) {
                deliveryAgentService = binder.getServiceBinder();
                deliveryAgentServiceBound = true;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"connectionDeliveryAgentService onServiceDisconnected(ComponentName componentName)");
        }
    };

    public void onBindAgents(View view) {
        Intent intentCoreAgentService = new Intent(this, CoreAgentService.class);
        bindService(intentCoreAgentService, connectionCoreAgentService, Context.BIND_AUTO_CREATE);

        Intent intentDeliveryAgentService = new Intent(this, DeliveryAgentService.class);
        bindService(intentDeliveryAgentService, connectionDeliveryAgentService, Context.BIND_AUTO_CREATE);
    }

    public void onUnbindAgents(View view) {
        unbindService(connectionCoreAgentService);
        unbindService(connectionDeliveryAgentService);
    }
}
