package com.sethu.androidtemplatecode.retrofitapiinterface;

import com.sethu.androidtemplatecode.model.CreateUserResponse;
import com.sethu.androidtemplatecode.model.User;
import com.sethu.androidtemplatecode.model.UserData;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiInterface {
    @GET("/api/users")
    Call<List<User>> getUsersInfo();
    @GET("/api/users/{userId}")
    Call<UserData> getUserInfo(@Path("userId") String userId);
    @POST("/api/users")
    @FormUrlEncoded
    Call<CreateUserResponse> createUser(@Field("name") String name,
                                        @Field("job") String job);
}