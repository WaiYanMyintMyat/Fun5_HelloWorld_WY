package com.padcmyanmar.fun5.helloworld.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.fun5.helloworld.R;
import com.padcmyanmar.fun5.helloworld.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //inflate pe twar yin so tae ...codition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void setEmptyData(String emptyImageUrl,String emptyMessage){
        GlideApp.with(getContext())
                .load(emptyImageUrl)
                .into(ivEmpty);

        tvEmpty.setText(emptyMessage);
    }

    public void setEmptyData(int emptyImageResource,String emptyMessage){
        ivEmpty.setImageResource(emptyImageResource);
        tvEmpty.setText(emptyMessage);
    }
}
