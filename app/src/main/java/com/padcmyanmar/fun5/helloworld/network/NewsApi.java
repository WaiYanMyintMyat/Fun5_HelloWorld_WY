package com.padcmyanmar.fun5.helloworld.network;

import com.padcmyanmar.fun5.helloworld.network.response.GetNewsResponse;
import com.padcmyanmar.fun5.helloworld.utils.MMNewsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsApi {

    @FormUrlEncoded
    @POST(MMNewsConstants.GET_NEWS)
    Call<GetNewsResponse> loadNewsList(
            @Field(MMNewsConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(MMNewsConstants.PARAM_PAGE) int page);
}
