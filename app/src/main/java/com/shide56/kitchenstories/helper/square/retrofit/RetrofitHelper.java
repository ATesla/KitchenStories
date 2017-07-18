package com.shide56.kitchenstories.helper.square.retrofit;

import com.shide56.kitchenstories.helper.gson.GsonHelper;
import com.shide56.kitchenstories.helper.square.okhttp.HttpUrlHelper;
import com.shide56.kitchenstories.helper.square.okhttp.OkHttpHelper;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit助手
 */

public class RetrofitHelper {
    private final static Retrofit retrofit = new Retrofit.Builder()
            .client(OkHttpHelper.getClient())
            .baseUrl(HttpUrlHelper.baseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RetrofitHelper() {
    }

    public static void enqueue(String pathSegments, Object src, Callback<ResponseBody> callback) {
        RequestBody body = RequestBody.create(OkHttpHelper.getContentType(), GsonHelper.toJson(src));
        enqueue(pathSegments, body, callback);
    }

    public static void enqueue(String pathSegments, RequestBody body, Callback<ResponseBody> callback) {
        retrofit
                .create(IService.class)
                .service(pathSegments, body)
                .enqueue(callback);

    }
}
