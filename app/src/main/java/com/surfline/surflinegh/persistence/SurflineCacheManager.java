package com.surfline.surflinegh.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.surfline.base.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SurflineCacheManager {

    private static SurflineCacheManager surflineCacheManager;
    private SharedPreferences sharedPreferences;
    private Context mContext;

    private SurflineCacheManager() {
        // Do nothing
    }

    private SurflineCacheManager(Context context) {
        mContext = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
    }

    public static SurflineCacheManager getInstance(Context context) {
        if (surflineCacheManager == null) {
            surflineCacheManager = new SurflineCacheManager(context);
        }
        return surflineCacheManager;
    }

    public static SurflineCacheManager getInstance(BaseFragment fragment) {
        return getInstance(fragment.getBaseActivity());
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setArrayList(String key, ArrayList arrayList) {
        Set<String> set = new HashSet<String>();
        set.addAll(arrayList);
        sharedPreferences.edit().putStringSet(key, set).apply();

    }

    public ArrayList<String> getArrayList(String key) {

        Set<String> set = sharedPreferences.getStringSet(key, new HashSet<String>());
        ArrayList<String> result = new ArrayList<String>(set);
        return result;

    }

    public void setLong(String key, Long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void removeKey(String[] keyList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : keyList) {
            editor.remove(key);
        }
        editor.commit();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void logOut() {
        String[] prefsList = {
                PrefrenceConstants.FIRST_NAME,
                PrefrenceConstants.LOGGED_IN,
                PrefrenceConstants.LAST_NAME,
                PrefrenceConstants.EMAIL,
                PrefrenceConstants.MOBILE_NO};

        removeKey(prefsList);

    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
