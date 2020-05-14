package org.qtproject.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.qtproject.qt5.android.bindings.QtService;

import android.util.Log;

public class LauncherActivity extends AppCompatActivity {

    static final String TAG="LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void onStartQtService(View view) {
        Log.i(TAG,"onStartQtService");
        Intent intent = new Intent(this, QtService.class);
        startService(intent);
    }

    public void onStopQtService(View view) {
        Log.i(TAG,"onStopQtService");
        Intent intent = new Intent(this, QtService.class);
        stopService(intent);
    }

}
