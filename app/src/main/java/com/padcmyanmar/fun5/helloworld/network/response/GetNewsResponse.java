package com.padcmyanmar.fun5.helloworld.network.response;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;

import java.util.List;

public class GetNewsResponse {
    //Api Specific
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("mmNews")
    private List<NewsVO> mmNews;

    //getter method for display
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewsVO> getMmNews() {
        return mmNews;
    }

    public boolean isRsponseOK(){
        return (code==200 && mmNews != null);//short term Take Note...if else ma use buu!!!
    }
}
