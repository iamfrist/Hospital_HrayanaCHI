package com.imf.haryanachi.network;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;


    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }



}
