package com.padcmyanmar.fun5.helloworld.data.models;

import com.padcmyanmar.fun5.helloworld.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.fun5.helloworld.network.NewsDataAgent;

public class NewsModel {
    //Singleton Pattern
    private static NewsModel objInstance;
    private NewsDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";
    private NewsModel() {
        mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
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

}
