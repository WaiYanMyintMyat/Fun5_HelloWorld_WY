package com.padcmyanmar.fun5.helloworld.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;
import com.padcmyanmar.fun5.helloworld.viewholders.BaseNewsViewHolder;
import com.padcmyanmar.fun5.helloworld.viewholders.NewsBriefViewHolder;
import com.padcmyanmar.fun5.helloworld.viewholders.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 5/27/18.
 */

//Generic type <NewsViewHolder>
public class NewsAdapter extends RecyclerView.Adapter<BaseNewsViewHolder> {

    private static final int VT_NEWS_COMPLETE = 1000;
    private static final int VT_NEWS_BRIEF = 2000;

    private NewsDelegate newsDelegate;
    private List<NewsVO> mNewsList;//m for member ,s for static

    public NewsAdapter(NewsDelegate newsDelegate) {
        mNewsList = new ArrayList<>();
        this.newsDelegate = newsDelegate;
    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VT_NEWS_COMPLETE) {
            View view = layoutInflater.inflate(R.layout.view_holder_news, parent, false);
            return new NewsViewHolder(view, newsDelegate);
        } else {
            View view = layoutInflater.inflate(R.layout.view_holder_news_brief,parent,false);
            return new NewsBriefViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder holder, int position) {
        //position parameter for collection(List)
        holder.bindData(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VT_NEWS_COMPLETE;
        } else {
            return VT_NEWS_BRIEF;
        }
    }

    public void setNewsList(List<NewsVO> newsList) {
        mNewsList = newsList;
        //extend loat htar loe//Refresh viewholder
        notifyDataSetChanged();
    }

    public void appendNewsList(List<NewsVO> newsList) {
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }
}
