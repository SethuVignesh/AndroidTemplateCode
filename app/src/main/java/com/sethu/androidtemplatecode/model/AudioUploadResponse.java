package com.sethu.androidtemplatecode.model;

import com.google.gson.annotations.SerializedName;

public class AudioUploadResponse {
    @SerializedName("success")
    private String success;
    @SerializedName("failure")
    private String failure;
    public AudioUploadResponse(String response) {
        this.success = response;
    }
    public String getResponse() {
        if(success != null){
            return success;
        }else {
            return failure;
        }
    }
}
