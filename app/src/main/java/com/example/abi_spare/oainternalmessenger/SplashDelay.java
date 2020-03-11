package com.example.abi_spare.oainternalmessenger;

import android.app.Application;
import android.os.SystemClock;

public class SplashDelay extends Application {
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
