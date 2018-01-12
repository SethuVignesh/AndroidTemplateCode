package com.sethu.androidtemplatecode.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    String userId;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("avatar")
    String avatar; /*gets image url path */

    public User(String userId, String firstName, String lastName,String avatar) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getUserId() { return userId; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAvatar() { return avatar; }
}