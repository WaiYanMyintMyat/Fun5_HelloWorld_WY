package com.padcmyanmar.fun5.helloworld.data.models;

import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.events.SuccessForceRefreshGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.fun5.helloworld.network.NewsDataAgent;
import com.padcmyanmar.fun5.helloworld.network.OkHttpDataAgentImpl;
import com.padcmyanmar.fun5.helloworld.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsModel {
    //Singleton Pattern
    private static NewsModel objInstance;
    private NewsDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    //Data Repository
    private Map<String, NewsVO> mNewsMap;//<String,NewsVO> String news_id use ya mal...//1.data unique ,2. get by ID
    private int mPage;

    private NewsModel() {
        //because of abstraction layer (Interface)
        //mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent=OkHttpDataAgentImpl.getObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getsObjInstance();

        mNewsMap = new HashMap<>();
        mPage=1;
        EventBus.getDefault().register(this);
    }

    public static NewsModel getObjInstance() {
        //Factory Pattern
        if (objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void loadNewsList() {
        mDataAgent.loadNewsList(mPage, DUMMY_ACCESS_TOKEN,false);
    }

    public void forceRefreshNewsList(){
        mPage=1;
        mDataAgent.loadNewsList(1,DUMMY_ACCESS_TOKEN,true);
    }

    public NewsVO getNewsById(String newsId) {
        //return null;//TODO remove this after empty view layout in news details screen.//test logic
        return mNewsMap.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetNewsEvent event) {
        setDataIntoRepository(event.getNewsList());
        mPage++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetNews(SuccessForceRefreshGetNewsEvent event) {
        setDataIntoRepository(event.getNewsList());
        mPage++;
    }

    private void setDataIntoRepository(List<NewsVO> newsList){
        for (NewsVO news : newsList) {
            mNewsMap.put(news.getNewId(), news);
        }
    }
}
