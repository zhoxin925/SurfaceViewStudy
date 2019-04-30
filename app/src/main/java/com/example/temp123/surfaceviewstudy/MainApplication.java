package com.example.temp123.surfaceviewstudy;

import android.app.Application;

import leakcanary.LeakCanary;

/**
 * Created by temp123 on 2019/4/30.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.INSTANCE.isInAnalyzerProcess(this))
        {

        }
        LeakCanary.INSTANCE.install().
    }
}
