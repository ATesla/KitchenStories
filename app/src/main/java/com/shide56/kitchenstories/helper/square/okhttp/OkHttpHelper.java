package com.shide56.kitchenstories.helper.square.okhttp;



import com.shide56.kitchenstories.helper.gson.GsonHelper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp助手
 */
public final class OkHttpHelper {
    private final static int TIMEOUT_CONNECT = 60;
    private final static int TIMEOUT_READ = 100;
    private final static int TIMEOUT_WRITE_ = 60;
    private final static MediaType contentType = MediaType.parse("text/html; charset=UTF-8");
    private final static OkHttpClient CLIENT = new OkHttpClient.Builder()
//            .cache(XApplication.cache)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(new NetworkInterceptor())
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE_, TimeUnit.SECONDS)
            .build();

    private OkHttpHelper() {
    }

    public static Cache getCache(File directory, long maxSize) {
        return new Cache(directory, maxSize);
    }

    @Deprecated
    public static void enqueue(String pathSegments, Object src, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        RequestBody body = RequestBody.create(contentType, GsonHelper.toJson(src));
        call(url, "POST", body, callback);
    }

    @Deprecated
    public static void enqueue(String pathSegments, RequestBody body, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        call(url, "POST", body, callback);
    }

    /**
     * 同步执行
     */
    @Deprecated
    public static Response execute(String pathSegments, String content) {
        RequestBody requestBody = RequestBody.create(contentType, content);
        Request request = new Request.Builder().
                url(HttpUrlHelper.build(pathSegments)).
                post(requestBody).
                build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public static Response execute(String pathSegments, RequestBody body) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        return call(url, "POST", body);
    }

    public static MediaType getContentType() {
        return contentType;
    }

    public static OkHttpClient getClient() {
        return CLIENT;
    }

    //=============================new start
    //REST架构之函数封装

    /**
     * HTTP GET: 获取资源
     */
    public static void get(String pathSegments, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        call(url, "GET", null, callback);
    }

    public static void get(HttpUrl url, Callback callback) {
        call(url, "GET", null, callback);
    }

    /**
     * HTTP PUT/POST: 创建/添加资源
     */
    public static void post(String pathSegments, Object src, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        RequestBody body = RequestBody.create(contentType, GsonHelper.toJson(src));
        call(url, "POST", body, callback);
    }

    public static void post(HttpUrl url, RequestBody body, Callback callback) {
        call(url, "POST", body, callback);
    }

    /**
     * HTTP PUT: 修改资源
     */
    public static void put(String pathSegments, Object src, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        RequestBody body = RequestBody.create(contentType, GsonHelper.toJson(src));
        call(url, "PUT", body, callback);
    }

    public static void put(HttpUrl url, RequestBody body, Callback callback) {
        call(url, "PUT", body, callback);
    }

    /**
     * HTTP DELETE: 删除资源
     */
    public static void delete(String pathSegments, Object src, Callback callback) {
        HttpUrl url = HttpUrlHelper.build(pathSegments);
        RequestBody body = RequestBody.create(contentType, GsonHelper.toJson(src));
        call(url, "DELETE", body, callback);
    }

    public static void delete(HttpUrl url, RequestBody body, Callback callback) {
        call(url, "DELETE", body, callback);
    }

    private static void call(HttpUrl url, String method, RequestBody body, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .build();
        CLIENT.newCall(request).enqueue(callback);
    }

    private static Response call(HttpUrl url, String method, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .build();
        try {
            return CLIENT.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //========================new end

}
