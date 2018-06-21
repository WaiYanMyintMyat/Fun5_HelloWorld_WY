package com.padcmyanmar.fun5.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.adapters.NewsAdapter;
import com.padcmyanmar.fun5.helloworld.data.models.NewsModel;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsListActivity extends BaseActivity
                                implements NewsDelegate{

    private NewsAdapter mNewsAdapter;//attribute , not local variable

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        MMFontUtils.initMMTextView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNews = findViewById(R.id.rv_news);

        mNewsAdapter = new NewsAdapter(this);
        rvNews.setAdapter(mNewsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        //Data Ping from network
        NewsModel.getObjInstance().loadNewsList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapNews(NewsVO news) {

        Intent intent = new Intent(getApplicationContext(),NewsDetailsActivity.class);
        intent.putExtra("newsId",news.getNewId());
        startActivity(intent);
    }

    @Override
    public void onTapFavourite(NewsVO news) {

    }

    @Override
    public void onTapComment(NewsVO news) {

    }

    @Override
    public void onTapSendTo(NewsVO news) {

    }

    @Override
    public void onTapStatistics(NewsVO news) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)//UI mhar display ya mhar mo lo Main Thread use tar...
    public void onSuccessGetNews(SuccessGetNewsEvent event){
        Log.d("onSuccessGetNews","onSuccessGetNews:"+event.getNewsList().size());
        mNewsAdapter.setNewsList(event.getNewsList());
    }
}
