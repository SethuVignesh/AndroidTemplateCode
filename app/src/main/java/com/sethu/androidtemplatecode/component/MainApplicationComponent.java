package com.sethu.androidtemplatecode.component;

//import com.sethu.androidtemplatecode.module.BroadcastModule;
import com.sethu.androidtemplatecode.module.NetworkModule;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {NetworkModule.class})
public interface MainApplicationComponent {
    Retrofit getRetrofitInstance();
}
