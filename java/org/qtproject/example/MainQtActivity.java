package org.qtproject.example;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.os.Bundle;

import org.qtproject.qt5.android.bindings.QtActivity;

import android.util.Log;

public class MainQtActivity extends QtActivity {
    static final String TAG="MainQtActivity DF";

    @Override
    public void onCreate(Bundle bundle) {
        Log.i(TAG, "onCreate()");
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_qt);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onServiceStart(View view) {
        Log.i(TAG,"onServiceStart()");
        MainService.startQtService(this);
    }

    public void onServiceStop(View view) {
        Log.i(TAG,"onServiceStop()");
        MainService.stopQtService(this);
    }

private MainService mainService;
private boolean bound = false;

// Связывание с службой
private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        // Код, выполняемый при связи со службой
        Log.i(TAG,"onServiceConnected(ComponentName componentName, IBinder iBinder)");

        MainService.ServiceBinder binder = (MainService.ServiceBinder)iBinder;
        mainService = binder.getServiceBinder();
        bound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        // Код, выполняемый при разрыве связи со службой
        Log.i(TAG,"onServiceDisconnected(ComponentName componentName)");
        bound = false;
    }
};


public void onBindService(View view)
{
    Log.i(TAG,"onBindService(View view)");
    Intent intent = new Intent(this, MainService.class);
    bindService(intent, connection, Context.BIND_AUTO_CREATE);
}

public void onUnBindService(View view)
{
    Log.i(TAG,"onUnBindService(View view)");
    if(bound)
    {
        unbindService(connection);
        bound = false;
    }
}
}

