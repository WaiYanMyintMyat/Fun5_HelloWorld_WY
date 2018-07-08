package com.padcmyanmar.fun5.helloworld.viewholders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsBriefViewHolder extends BaseNewsViewHolder {
    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_news_hero)
    ImageView ivNewsHero;

    private NewsVO mNews;

    public NewsBriefViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindData(NewsVO news){
        mNews=news;
        tvNewsBrief.setText(news.getBrief());

        tvPublicationTitle.setText(mNews.getPublication().getTitle());

        if(!mNews.getImages().isEmpty()){
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
