package com.padcmyanmar.fun5.helloworld.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;

public abstract class BaseNewsViewHolder extends RecyclerView.ViewHolder {
    public BaseNewsViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(NewsVO news){

    }
}
