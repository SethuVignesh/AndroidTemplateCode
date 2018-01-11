package com.sethu.androidtemplatecode.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sethu.androidtemplatecode.R;
import com.sethu.androidtemplatecode.application.MainApplication;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;

import retrofit2.Retrofit;

public class RetrofitDemoActivity extends AppCompatActivity {
    MainApplicationComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        getMainApplicationComponent();
    }

    Retrofit getRetrofitInstance(){
       return component.getRetrofitInstance();
    }

    void  getMainApplicationComponent(){
        component = MainApplication.getMainApplicationComponent();
    }

}
