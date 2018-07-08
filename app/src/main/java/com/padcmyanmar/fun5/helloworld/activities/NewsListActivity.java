package com.padcmyanmar.fun5.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.adapters.NewsAdapter;
import com.padcmyanmar.fun5.helloworld.data.models.NewsModel;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;
import com.padcmyanmar.fun5.helloworld.events.ApiErrorEvent;
import com.padcmyanmar.fun5.helloworld.events.SuccessForceRefreshGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsListActivity extends BaseActivity
        implements NewsDelegate {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private NewsAdapter mNewsAdapter;//attribute , not local variable

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this, this);

        MMFontUtils.initMMTextView(this);

        setSupportActionBar(toolbar);

        mNewsAdapter = new NewsAdapter(this);

        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isListEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("NewsListActivity", "OnScrollListener:onScrollStateChanged" + newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                        == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    NewsModel.getObjInstance().loadNewsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dx delta x
                //dy delta y
                super.onScrolled(recyclerView, dx, dy);
                Log.d("NewsListActivity", "OnScrollListener:onScrolled - dx : " + dx + ", dy : " + dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();//3
                int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisibleItems) < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });

        rvNews.setAdapter(mNewsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        //Data Ping from network
        NewsModel.getObjInstance().loadNewsList();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //load Again
                NewsModel.getObjInstance().forceRefreshNewsList();
            }
        });

        vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapNews(NewsVO news) {

        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("newsId", news.getNewId());
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
    public void onSuccessGetNews(SuccessGetNewsEvent event) {
        Log.d("onSuccessGetNews", "onSuccessGetNews:" + event.getNewsList().size());
        mNewsAdapter.appendNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//UI mhar display ya mhar mo lo Main Thread use tar...
    public void onSuccessForceResultGetNews(SuccessForceRefreshGetNewsEvent event) {
        mNewsAdapter.setNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//UI mhar display ya mhar mo lo Main Thread use tar...
    public void onFailGetNews(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);

//        if(!event.getErrorMessage().equalsIgnoreCase("success")){
//            vpEmpty.setVisibility(View.VISIBLE);
//            Snackbar.make(swipeRefreshLayout,event.getErrorMessage(),Snackbar.LENGTH_INDEFINITE).show();//Snack bar show error use indefinite
//        }
        if (mNewsAdapter.getItemCount() <= 0){
            vpEmpty.setVisibility(View.VISIBLE);
            Snackbar.make(swipeRefreshLayout,event.getErrorMessage(),Snackbar.LENGTH_INDEFINITE).show();//Snack bar show error use indefinite
        }
    }
}
