package com.padcmyanmar.fun5.helloworld.data.vos;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/9/18.
 */
public class NewsVO {

    private String newId;
    private String brief;
    private String details;
    private List<String> images;
    private String postedDate;
    private PublicationVO publication;
    private List<FavouriteVO> favourite;
    private List<CommentVO> comments;
    private List<SentToVO> sentTos;


    public String getNewId() {
        return newId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavouriteVO> getFavourite() {
        return favourite;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public List<SentToVO> getSentTos() {
        return sentTos;
    }
}
