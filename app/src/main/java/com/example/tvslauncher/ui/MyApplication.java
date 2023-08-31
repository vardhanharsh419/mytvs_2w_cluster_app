package com.example.tvslauncher.ui;

import android.app.Application;

import com.example.tvslauncher.MainActivity;

import ru.skornei.restserver.RestServerManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RestServerManager.initialize(this);

    }
}
