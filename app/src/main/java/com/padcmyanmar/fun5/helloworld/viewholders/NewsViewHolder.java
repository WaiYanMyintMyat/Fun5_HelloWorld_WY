package com.padcmyanmar.fun5.helloworld.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.fun5.helloworld.delegates.NewsDelegate;

/**
 *
 * Created by Phyo Thiha on 5/27/18.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private NewsDelegate mNewsDelegate;
    public NewsViewHolder(View itemView, NewsDelegate newsDelegate) {
        super(itemView);
        mNewsDelegate = newsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsDelegate.onTapNews();
            }
        });
    }
}
