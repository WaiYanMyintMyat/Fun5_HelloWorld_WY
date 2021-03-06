package com.padcmyanmar.fun5.helloworld.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;
import com.padcmyanmar.fun5.helloworld.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsViewHolder extends BaseNewsViewHolder {

    private NewsDelegate mNewsDelegate;
    private NewsVO mNews;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_publisher_image)
    ImageView ivPublisherImage;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.iv_news_hero)
    ImageView ivNewsHero;


    public NewsViewHolder(View itemView, NewsDelegate newsDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mNewsDelegate = newsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsDelegate.onTapNews(mNews);
            }
        });
    }

    @Override
    public void bindData(NewsVO news) {
        super.bindData(news);
        mNews=news;
        tvNewsBrief.setText(news.getBrief());

        GlideApp.with(ivPublisherImage.getContext())
                .load(mNews.getPublication().getLogo())
                .apply(RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.news_placeholder)
                        .error(R.drawable.news_error))
                .into(ivPublisherImage);

        tvPublicationTitle.setText(mNews.getPublication().getTitle());

        tvPostedDate.setText(tvPostedDate.getContext()
                .getResources().getString(R.string.format_posted_date,mNews.getPostedDate()));

        if(!mNews.getImages().isEmpty()){
            ivNewsHero.setVisibility(View.VISIBLE);
            GlideApp.with(ivNewsHero.getContext())
                    .load(mNews.getImages().get(0))
                    .placeholder(R.drawable.news_placeholder)
                    .error(R.drawable.news_error)
                    .into(ivNewsHero);
        }else{
            ivNewsHero.setVisibility(View.GONE);
        }
    }
}
