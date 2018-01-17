package com.sethu.androidtemplatecode.model;

import com.google.gson.annotations.SerializedName;
import java.util.Comparator;

public class User implements Comparator<User> {
    @SerializedName("id")
    String userId;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("avatar")
    String avatar;

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

    @Override
    public int hashCode() {
        return (this.getUserId().hashCode() + this.getFirstName().hashCode()+this.getLastName().hashCode()+this.getAvatar().hashCode());
    }

    @Override
    public int compare(User o1, User o2) {
        if(o1.getUserId().equals(o2.getUserId()))
            return 0;
        if(!o1.getUserId().equals(o2.getUserId()))
            return 1;
        else
            return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
        {
            User temp = (User) obj;
            if(this.userId.equals( temp.userId)
                    && this.firstName.equals( temp.firstName)
                    && this.lastName.equals( temp.lastName)
                    && this.avatar.equals( temp.avatar))
                return true;
        }
        return false;
    }
}