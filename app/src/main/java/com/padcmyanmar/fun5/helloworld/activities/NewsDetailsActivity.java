package com.padcmyanmar.fun5.helloworld.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.data.models.NewsModel;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.viewpods.EmptyViewPod;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_news_content)
    TextView tvNewsContent;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this,this);

        String newsId = getIntent().getStringExtra("newsId");
        Log.d("NewsDetailsActivity", newsId);

        NewsVO news= NewsModel.getObjInstance().getNewsById(newsId);
        if(news!=null){
            bindData(news);
        }else{
            coordinatorLayout.setVisibility(View.GONE);
            vpEmpty.setVisibility(View.VISIBLE);
        }
        vpEmpty.setEmptyData("http://i0.kym-cdn.com/photos/images/facebook/000/248/079/310.jpg",getString(R.string.empty_msg_news_detail));
    }

    private void bindData(NewsVO news) {
        tvNewsContent.setText(news.getDetails());
    }
}
