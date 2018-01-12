package com.sethu.androidtemplatecode.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserEnvelop<T> {
    @SerializedName("page")
    int page;
    @SerializedName("per_page")
    int per_page;
    @SerializedName("total")
    int total;
    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("data")
    List<User> data;
}
//url https://reqres.in/api/users
// {
//        "page":1,
//        "per_page":3,
//        "total":12,
//        "total_pages":4,
//        "data":[
//        {
//        "id":1,
//        "first_name":"George",
//        "last_name":"Bluth",
//        "avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"
//        },
//          ...
//        ]
//        }