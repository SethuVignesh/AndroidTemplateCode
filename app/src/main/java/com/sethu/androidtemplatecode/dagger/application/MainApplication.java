package com.sethu.androidtemplatecode.dagger.application;

import android.app.Application;

import com.sethu.androidtemplatecode.dagger.component.DaggerMainApplicationComponent;
import com.sethu.androidtemplatecode.dagger.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.dagger.module.NetworkModule;


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
        if(component == null){
            component = DaggerMainApplicationComponent.builder()
                    .networkModule(new NetworkModule())
                    .build();
        }
        return component;
    }

}
