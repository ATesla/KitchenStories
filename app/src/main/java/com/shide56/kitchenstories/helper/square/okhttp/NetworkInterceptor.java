package com.shide56.kitchenstories.helper.square.okhttp;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器 篡改本次请求和响应
 */

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString().replace("%3F", "?");
        request = request
                .newBuilder()
                .url(url)
                .build();

        Response response = chain.proceed(request);
        /*response = response
                .newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //cache for 30 days
                .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                .build();*/

        return response;
    }
}
