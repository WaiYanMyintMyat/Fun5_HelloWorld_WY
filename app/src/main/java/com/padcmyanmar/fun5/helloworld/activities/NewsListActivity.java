package com.padcmyanmar.fun5.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.adapters.NewsAdapter;
import com.padcmyanmar.fun5.helloworld.data.models.NewsModel;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;

import org.mmtextview.MMFontUtils;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsListActivity extends BaseActivity
                                implements NewsDelegate{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        MMFontUtils.initMMTextView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNews = findViewById(R.id.rv_news);

        NewsAdapter newsAdapter = new NewsAdapter(this);
        rvNews.setAdapter(newsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        NewsModel.getObjInstance().loadNewsList();
    }

    @Override
    public void onTapNews() {

        Intent intent = new Intent(getApplicationContext(),NewsDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTapFavourite() {

    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapStatistics() {

    }
}
