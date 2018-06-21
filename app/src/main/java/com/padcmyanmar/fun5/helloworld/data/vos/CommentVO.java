package com.padcmyanmar.fun5.helloworld.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class CommentVO {

    @SerializedName("comment-id")
    private String commentID;

    @SerializedName("comment")
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getCommentID() {
        return commentID;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}
