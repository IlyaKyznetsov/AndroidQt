package ru.aorr.Launcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.qtproject.qt5.android.bindings.QtActivity;

public class DriverControl extends QtActivity {
    static final String TAG = "ru.aorr.Launcher.DriverControl";
    private final void logging(String message, boolean isNotification) {
        Log.w(TAG, message);
        if (isNotification) {
            Toast.makeText(getApplicationContext(), TAG + " : " + message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        logging("onCreate()", true);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        logging("onStart()", true);
        super.onStart();
    }

    @Override
    protected void onResume() {
        logging( "onResume()", true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        logging( "onPause()", true);
        super.onPause();
    }

    @Override
    protected void onStop() {
        logging( "onStop()", true);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        logging("onDestroy()", false);
        super.onDestroy();
    }
}
