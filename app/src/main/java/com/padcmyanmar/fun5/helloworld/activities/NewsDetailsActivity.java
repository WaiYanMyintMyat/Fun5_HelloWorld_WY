package com.padcmyanmar.fun5.helloworld.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.data.models.NewsModel;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_news_content)
    TextView tvNewsContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this,this);

        String newsId = getIntent().getStringExtra("newsId");
        Log.d("NewsDetailsActivity", newsId);

        NewsVO news= NewsModel.getObjInstance().getNewsById(newsId);
        bindData(news);
    }

    private void bindData(NewsVO news) {
        tvNewsContent.setText(news.getDetails());
    }
}
