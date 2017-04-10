package com.surfline.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jyoti Sheoran Bhambhu on 8/4/16.
 */
public class ToastUtil {

    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;


    public static void showToast(Context mContext, String message, int timeDuration) {
        Toast.makeText(mContext, message, timeDuration).show();
    }

    public static void showLongToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
