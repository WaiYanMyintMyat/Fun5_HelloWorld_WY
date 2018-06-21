package com.padcmyanmar.fun5.helloworld.delegates;

import com.padcmyanmar.fun5.helloworld.data.vos.NewsVO;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public interface NewsDelegate {

    /**
     *
     */
    void onTapNews(NewsVO news);
    void onTapFavourite(NewsVO news);
    void onTapComment(NewsVO news);
    void onTapSendTo(NewsVO news);
    void onTapStatistics(NewsVO news);

}
