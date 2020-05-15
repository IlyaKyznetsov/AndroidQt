package org.qtproject.example;

// https://doc-snapshots.qt.io/qt5-5.15/android-services.html
import org.qtproject.qt5.android.bindings.QtService;
import android.util.Log;
public class ApartService extends QtService {
    static final String TAG="qtApartService";
    @Override
    public void onCreate()
    {
       super.onCreate();
        Log.i(TAG,"qt ---- onStartApart");
    }


}
