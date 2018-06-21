package com.padcmyanmar.fun5.helloworld.data.models;

import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.fun5.helloworld.network.NewsDataAgent;
import com.padcmyanmar.fun5.helloworld.network.OkHttpDataAgentImpl;
import com.padcmyanmar.fun5.helloworld.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewsModel {
    //Singleton Pattern
    private static NewsModel objInstance;
    private NewsDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";

    //Data Repository
    private Map<String, NewsVO> mNewsMap;//<String,NewsVO> String news_id use ya mal...

    private NewsModel() {
        //because of abstraction layer (Interface)
        //mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent=OkHttpDataAgentImpl.getObjInstance();
        mDataAgent= RetrofitDataAgentImpl.getsObjInstance();

        mNewsMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static NewsModel getObjInstance(){
        //Factory Pattern
        if(objInstance==null){
            objInstance=new NewsModel();
        }
        return objInstance;
    }

    public void loadNewsList(){
        mDataAgent.loadNewsList(1,DUMMY_ACCESS_TOKEN);
    }

    public NewsVO getNewsById(String newsId){
        return mNewsMap.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetNewsEvent event){
        for(NewsVO news : event.getNewsList()){
            mNewsMap.put(news.getNewId(),news);
        }
    }
}
