package com.sethu.androidtemplatecode.retrofitapiinterface;

import com.sethu.androidtemplatecode.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiInterface {
    @GET("/api/users")
    Call<List<User>> getUsersInfo();
}