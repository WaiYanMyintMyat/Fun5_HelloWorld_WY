package com.padcmyanmar.fun5.helloworld.network;

public interface NewsDataAgent {
    //Description actions of News DataAgent//void for Asynchronous
    void loadNewsList(int page,String accessToken,boolean isForceRefresh);
}
