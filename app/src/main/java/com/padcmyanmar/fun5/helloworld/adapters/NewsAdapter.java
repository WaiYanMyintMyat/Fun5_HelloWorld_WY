package com.padcmyanmar.fun5.helloworld.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;
import com.padcmyanmar.fun5.helloworld.viewholders.NewsViewHolder;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsAdapter extends RecyclerView.Adapter {

    private NewsDelegate newsDelegate;
    public NewsAdapter(NewsDelegate newsDelegate){
        this.newsDelegate = newsDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.view_holder_news,parent,false);

        return new NewsViewHolder(view,newsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
