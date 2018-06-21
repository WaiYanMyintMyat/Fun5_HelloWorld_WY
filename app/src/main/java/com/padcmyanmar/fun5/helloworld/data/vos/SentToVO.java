package com.padcmyanmar.fun5.helloworld.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class SentToVO {

    @SerializedName("send-to-id")
    private String sentToId;

    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @SerializedName("received-user")
    private ActedUserVO recievedUser;

    public String getSentToId() {
        return sentToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ActedUserVO getRecievedUser() {
        return recievedUser;
    }
}
