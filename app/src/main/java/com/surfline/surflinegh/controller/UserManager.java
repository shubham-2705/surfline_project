package com.surfline.surflinegh.controller;

import android.content.Context;
import android.content.Intent;

import com.surfline.base.ui.activity.BaseActivity;
import com.surfline.surflinegh.persistence.SurflineCacheManager;
import com.surfline.surflinegh.ui.activities.WelcomeActivity;


public class UserManager {

    public static void clearUserData(Context mContext) {
        clearUserData(mContext, SurflineCacheManager.getInstance(mContext));
    }

    public static void clearUserData(Context mContext, SurflineCacheManager prefManager) {
        if (prefManager == null)
            return;
        prefManager.clearAll();
    }

    public static void logoutUser(Context mContext) {
        logoutUser(mContext, SurflineCacheManager.getInstance(mContext));
    }

    public static void logoutUser(Context mContext, SurflineCacheManager prefManager) {
        clearUserData(mContext, prefManager);
        Intent intent = new Intent(mContext, WelcomeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
        ((BaseActivity) mContext).finish();
    }
}
