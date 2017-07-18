package com.shide56.kitchenstories.helper.square.retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * api接口封装类
 */

public interface IService {

    @POST("{pathSegments}")
    Call<ResponseBody> service(@Path(value = "pathSegments", encoded = true) String pathSegments,
                               @Body RequestBody body);
}
