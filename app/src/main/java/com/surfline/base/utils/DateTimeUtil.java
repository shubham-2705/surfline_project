package com.surfline.base.utils;

import android.text.Html;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jyoti Sheoran Bhambhu on 2/5/16.
 */
public class DateTimeUtil {

    public static String formatDate(String inputFormat, String outputFormat, String date) {
        if (TextUtils.isEmpty(date))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        SimpleDateFormat output = new SimpleDateFormat(outputFormat, Locale.ENGLISH);
        Date d = null;
        try {
            d = sdf.parse(date);
            return output.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDate(String dateFormat, long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        if (milliSeconds == 0)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String formatDate(String dateFormat, Calendar calendar) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return formatter.format(calendar.getTime());
    }

//    public static String getDateInSuffixFormat(Calendar calDate) {
//        String dayNumberSuffix = getDayNumberSuffix(calDate.get(Calendar.DAY_OF_MONTH));
////        DateFormat dateFormat = new SimpleDateFormat("d" +  " MMMM, E", Locale.US);
//        DateFormat dateFormat = new
//                SimpleDateFormat(Html.fromHtml("d' <sup>" + dayNumberSuffix + "</sup>") +
//                "' MMMM yyyy", Locale.US);
//        return dateFormat.format(calDate.getTime());
//    }

    public static Date getDate(String dateFormat, String dateString) {
        if (TextUtils.isEmpty(dateString))
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateInSuffixFormat(String dateFormat, String dateString) {
        if (TextUtils.isEmpty(dateString))
            return "";
        DateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(date);
        String dayNumberSuffix = getDayNumberSuffix(calDate.get(Calendar.DAY_OF_MONTH));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dayNumberSuffix = Html.fromHtml("d'<sup>" + dayNumberSuffix + "</sup>",Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            dayNumberSuffix = Html.fromHtml("d'<sup>" + dayNumberSuffix + "</sup>").toString();
        }
        DateFormat outputDateFormat = new
                SimpleDateFormat(dayNumberSuffix +
                "' MMMM yyyy", Locale.US);
        return outputDateFormat.format(date);
    }

    private static String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "<sup>th</sup>";
        }
        switch (day % 10) {
            case 1:
                return "<sup>st</sup>";
            case 2:
                return "<sup>nd</sup>";
            case 3:
                return "<sup>rd</sup>";
            default:
                return "<sup>th</sup>";
        }
    }

    public static long getDiffInDays(long date1, long date2) {
        return (date2 - date1) / (24 * 60 * 60 * 1000);
    }
}
