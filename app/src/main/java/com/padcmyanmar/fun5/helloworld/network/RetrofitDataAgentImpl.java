package com.padcmyanmar.fun5.helloworld.network;

import com.padcmyanmar.fun5.helloworld.events.ApiErrorEvent;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.network.response.GetNewsResponse;
import com.padcmyanmar.fun5.helloworld.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewsDataAgent {
    private static RetrofitDataAgentImpl sObjInstance;

    private NewsApi mTheApi;

    private RetrofitDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MMNewsConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(NewsApi.class);
    }

    public static RetrofitDataAgentImpl getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewsList(int page, String accessToken) {
        Call<GetNewsResponse> loadNewsCall = mTheApi.loadNewsList(accessToken, page);
        loadNewsCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.isRsponseOK()) {
                    SuccessGetNewsEvent event = new SuccessGetNewsEvent(newsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                } else {
                    if (newsResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty Response in network call.");
                        EventBus.getDefault().post(errorEvent);
                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(newsResponse.getMessage());//eg.Access forbidden
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                //Server Fail,Api Crash,etc...
                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
