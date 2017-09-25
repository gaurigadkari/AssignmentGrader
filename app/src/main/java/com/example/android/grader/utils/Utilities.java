package com.example.android.grader.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class Utilities {
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String changeDateFormat(String dateInString, String outputFormat) {
        String inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);
        Date date;
        String str = null;
        try {
            date = inputDateFormat.parse(dateInString);
            str = outputDateFormat.format(date);
        } catch (Exception e) {

        }
        return str;
    }
}
