package org.qtproject.example;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import org.qtproject.qt5.android.extras.QtAndroidServiceConnection;

import java.util.List;


public class LauncherActivity extends Activity {

    static final String TAG="qt_LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Log.i(TAG,"onCreate(Bundle savedInstanceState)");
    }

    private QtAndroidServiceConnection coreAgentServiceConnection;
    private QtAndroidServiceConnection deliveryAgentServiceConnection;

    private void setup(boolean setEnable)
    {
        if(true == setEnable)
        {
//            bindService(new Intent(this, CoreAgentService.class), coreAgentServiceConnection, Context.BIND_AUTO_CREATE);
//            bindService(new Intent(this, DeliveryAgentService.class), deliveryAgentServiceConnection, Context.BIND_AUTO_CREATE);

            Intent intent = new Intent(this, DriverControl.class);
            intent.putExtra("START",true);
            startActivity(intent);
        }
        else
        {
//            unbindService(coreAgentServiceConnection);
//            unbindService(deliveryAgentServiceConnection);

            Intent intent = new Intent(this, DriverControl.class);
            intent.putExtra("STOP",false);
            startActivity(intent);

        }
    }

    // ----------------------------------------------------------------------------
    public void onBindAgents(View view) {
        setup(true);
    }

    public void onUnbindAgents(View view) {
        setup(false);
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
