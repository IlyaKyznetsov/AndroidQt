package org.qtproject.example;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html

import android.util.Log;

import org.qtproject.qt5.android.bindings.QtService;
public class ApartService extends QtService {
    static final String TAG="qtApartService";
    @Override
    public void onCreate()
    {
       super.onCreate();
        Log.i(TAG,"qt ---- onStartApart");
    }


}
