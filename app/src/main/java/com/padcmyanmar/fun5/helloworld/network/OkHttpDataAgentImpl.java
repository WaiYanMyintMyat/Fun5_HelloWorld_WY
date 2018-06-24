package com.padcmyanmar.fun5.helloworld.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.fun5.helloworld.events.ApiErrorEvent;
import com.padcmyanmar.fun5.helloworld.events.SuccessGetNewsEvent;
import com.padcmyanmar.fun5.helloworld.network.response.GetNewsResponse;
import com.padcmyanmar.fun5.helloworld.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements NewsDataAgent {
    private static OkHttpDataAgentImpl objInstance;
    private static OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance(){
        if(objInstance==null){
            objInstance=new OkHttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadNewsList(final int page, final String accessToken) {
        NetworkCallTask networkCallTask=new NetworkCallTask(accessToken,page);//don't pass ui component .....to avoid memory leaked
        networkCallTask.execute();
    }

    //static inner class is to avoid memory leaked...
    private static class NetworkCallTask extends AsyncTask<Void,Void,String>{

        private String maccessToken;
        private int mPage;

        public NetworkCallTask(String accessToken, int page) {
            maccessToken = accessToken;
            mPage = page;
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody formBody = new FormBody.Builder() //2.
                    .add(MMNewsConstants.PARAM_ACCESS_TOKEN, maccessToken)
                    .add(MMNewsConstants.PARAM_PAGE, String.valueOf(mPage))
                    .build();

            Request request = new Request.Builder() //3
                    .url(MMNewsConstants.API_BASE + MMNewsConstants.GET_NEWS)
                    .post(formBody)
                    .build();

            try {
                Response response = mHttpClient.newCall(request).execute(); //4.
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
//                        AttractionListResponse responseAttractionList = CommonInstances.getGsonInstance().fromJson(responseString, AttractionListResponse.class);
//                        List<AttractionVO> attractionList = responseAttractionList.getAttractionList();
                    return responseString;
                } else {
                    //AttractionModel.getInstance().notifyErrorInLoadingAttractions(response.message());
                }
            } catch (IOException e) {
                Log.e("error", e.getMessage());
                //AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);

            Gson gson=new Gson();
            GetNewsResponse newsResponse=gson.fromJson(responseString, GetNewsResponse.class);
            //Log.d("onPostExecute","News List Size:"+newsResponse.getMmNews().size());

            if(newsResponse.isRsponseOK()){
                SuccessGetNewsEvent event=new SuccessGetNewsEvent(newsResponse.getMmNews());
                EventBus.getDefault().post(event);
            }else{
                ApiErrorEvent errorEvent=new ApiErrorEvent(newsResponse.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        }
    }
}
