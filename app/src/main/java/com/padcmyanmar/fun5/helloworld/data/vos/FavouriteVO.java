package com.padcmyanmar.fun5.helloworld.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class FavouriteVO {
    @SerializedName("favorite-id")
    private String favouriteId;

    @SerializedName("favorite-date")
    private String favouriteDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavouriteId() {
        return favouriteId;
    }

    public String getFavouriteDate() {
        return favouriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}
