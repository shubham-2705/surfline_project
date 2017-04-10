package com.surfline.base.application;

import android.app.Application;

import com.surfline.base.network.VolleyManager;


public class BaseApplication extends Application {
    private VolleyManager volleyManager;
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        volleyManager = VolleyManager.getInstance(this);
        // context=this;
    }
    public VolleyManager getVolleyManager() {
        if (volleyManager == null)
            volleyManager = VolleyManager.getInstance(this);
        return volleyManager;
    }

}
