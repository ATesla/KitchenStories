package com.shide56.kitchenstories.helper.square.okhttp;

import okhttp3.HttpUrl;

/**
 * url助手
 */

public final class HttpUrlHelper {
    /**
     * 0_开发库(Development Library)   demo.shide56.com:8060
     * 1_测试库(Test library)          test.shide56.com:80
     * 2_正式库(Official library)      daka.shide56.com:86
     */
    private final static HttpUrl baseUrl = new HttpUrl
            .Builder()
            .scheme("http")
            .host("test.shide56.com")
            .port(80)
            .addPathSegment("daka")
            .build();
    private static String scheme = baseUrl.scheme();
    private static String host = baseUrl.host();
    private static int port = baseUrl.port();

    private HttpUrlHelper() {
    }

    public static HttpUrl build(String pathSegments) {
        return new HttpUrl.Builder()
                .scheme(scheme)
                .host(host)
                .port(port)
                .addPathSegment(baseUrl.pathSegments().get(0))
                .addPathSegments(pathSegments)
                .build();
    }

    public static String baseUrl() {
        return baseUrl + "/";
    }

}
