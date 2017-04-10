package com.surfline.base.utils;


import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Jyoti Sheoran Bhambhu on 7/4/16.
 */
public class StringUtil {

    public static String getFormattedString(String rawString, boolean withDecimal) {

        if (TextUtils.isEmpty(rawString))
            rawString = "0";
        if (rawString.contains(","))
            return rawString;
        if (withDecimal)
            return String.format(Locale.US, "%,.2f", Float.parseFloat(rawString));
        else {
            DecimalFormat df = new DecimalFormat("#,###.##");
            return df.format(Float.parseFloat(rawString));
        }

    }

    public static String getCardFormat(String rawString, char formatter) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(formatter);
        DecimalFormat df = new DecimalFormat("####,####,####,##", dfs);
        return df.format(Float.parseFloat(rawString));
    }

}
