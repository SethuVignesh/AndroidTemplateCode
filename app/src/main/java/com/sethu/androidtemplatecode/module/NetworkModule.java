package com.sethu.androidtemplatecode.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sethu.androidtemplatecode.model.EmptyToNullConverter;
import com.sethu.androidtemplatecode.model.UserEnvelopConverter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {
    OkHttpClient client;
    GsonBuilder gsonBuilder;
    Retrofit retrofit;
    Retrofit retrofit2;
    @Provides
    Gson provideGson() {
       if(gsonBuilder == null) {
           gsonBuilder = new GsonBuilder();
           gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
       }
        return gsonBuilder.create();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        if(client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    @Provides
    @Named("multi_user_data")
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        if(retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .addConverterFactory(new EmptyToNullConverter())
                    .addConverterFactory(new UserEnvelopConverter())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://reqres.in")
                    .client(okHttpClient)
                    .build();
        }
        return retrofit2;
    }

    @Provides
    @Named("single_user_data")
    Retrofit provideRetrofitInstance(Gson gson, OkHttpClient okHttpClient) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(new EmptyToNullConverter())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://reqres.in")
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
