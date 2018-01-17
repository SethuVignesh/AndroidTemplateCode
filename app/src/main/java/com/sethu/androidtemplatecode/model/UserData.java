package com.sethu.androidtemplatecode.model;


import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("data")
    User data;

    public User getData() {
        return data;
    }
}
