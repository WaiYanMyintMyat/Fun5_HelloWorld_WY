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
import com.padcmyanmar.fun5.helloworld.viewholders.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

//Generic type <NewsViewHolder>
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private NewsDelegate newsDelegate;
    private List<NewsVO> mNewsList;//m for member ,s for static

//    public NewsAdapter(){
//        mNewsList=new ArrayList<>();
//    }
    public NewsAdapter(NewsDelegate newsDelegate){
        mNewsList=new ArrayList<>();
        this.newsDelegate = newsDelegate;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.view_holder_news,parent,false);

        return new NewsViewHolder(view,newsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        //position parameter for collection(List)
        holder.setNewsData(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList(List<NewsVO> newsList){
        mNewsList=newsList;
        //extend loat htar loe//Refresh viewholder
        notifyDataSetChanged();
    }
}
