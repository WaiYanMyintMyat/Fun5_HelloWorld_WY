package com.padcmyanmar.fun5.helloworld.data.vos;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class SentToVO {

    private String sentToId;
    private String sentDate;
    private ActedUserVO actedUser;
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
