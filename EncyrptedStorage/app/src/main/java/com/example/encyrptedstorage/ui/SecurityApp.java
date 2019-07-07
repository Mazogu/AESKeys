package com.example.encyrptedstorage.ui;

import android.app.Application;

import com.example.encyrptedstorage.dagger.component.AppComponent;
import com.example.encyrptedstorage.dagger.component.DaggerAppComponent;
import com.example.encyrptedstorage.dagger.module.AppModule;

public class SecurityApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public AppComponent getAppComponent(){return appComponent;}
}
