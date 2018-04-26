package com.ralo.unbeatable;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class UnbeatableApp extends Application{
    private static Context appContext;
    private static AppCompatActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static void setCurrentActivity(AppCompatActivity currentActivity){
        UnbeatableApp.currentActivity = currentActivity;
    }

    public static AppCompatActivity getCurrentActivity(){
        return currentActivity;
    }

    public static Context getInstance(){
        return appContext;
    }
}
