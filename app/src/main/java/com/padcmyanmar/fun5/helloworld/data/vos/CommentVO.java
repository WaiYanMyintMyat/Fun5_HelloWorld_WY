package com.padcmyanmar.fun5.helloworld.data.vos;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class CommentVO {

    private String commentID;
    private String comment;
    private String commentDate;
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
