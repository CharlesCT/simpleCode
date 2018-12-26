package com.example.administrator.myprogressbar.myApplication;

import android.app.Application;

/**
 * Created by Charles on 2018/11/13.
 * com.example.administrator.myprogressbar.myApplication
 */

public class MyApplication extends Application {

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MyApplication.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }






}
