package com.sethu.androidtemplatecode.component;

//import com.sethu.androidtemplatecode.module.BroadcastModule;
import com.sethu.androidtemplatecode.module.NetworkModule;

import javax.inject.Named;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {NetworkModule.class})
public interface MainApplicationComponent {
    @Named("multi_user_data")
    Retrofit getRetrofitMultiUserConverterInstance();
    @Named("single_user_data")
    Retrofit getRetrofitSingleUserConverterInstance();

}
