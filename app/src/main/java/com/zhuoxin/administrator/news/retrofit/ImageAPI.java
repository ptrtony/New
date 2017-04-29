package com.zhuoxin.administrator.news.retrofit;

import com.zhuoxin.administrator.news.entity.ImageJsonBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/1/13.
 */

public interface ImageAPI {
    @GET("api/data/福利/10/1")
    Call<ImageJsonBean> getRespons();
    @GET("api/data/Android/10/1")
    Call<ImageJsonBean> getAndroidRespons();
}
