package com.padcmyanmar.fun5.helloworld.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class ActedUserVO {
    @SerializedName("user-id")
    private String userId;

    @SerializedName("user-name")
    private String userName;

    @SerializedName("profile-image")
    private String profileImage;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
