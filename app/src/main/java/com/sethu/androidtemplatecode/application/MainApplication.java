package com.sethu.androidtemplatecode.application;

import android.app.Application;

import com.sethu.androidtemplatecode.component.DaggerMainApplicationComponent;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.module.NetworkModule;


public class MainApplication extends Application {

    static MainApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public static MainApplicationComponent getMainApplicationComponent() {
        return component;
    }

}
