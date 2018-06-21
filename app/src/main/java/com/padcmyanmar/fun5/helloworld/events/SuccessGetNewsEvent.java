package com.padcmyanmar.fun5.helloworld.events;

import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;

import java.util.List;

public class SuccessGetNewsEvent {
    private List<NewsVO> newsList;

    public SuccessGetNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
