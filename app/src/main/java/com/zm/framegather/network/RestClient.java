package com.zm.framegather.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description 描述
 * Created by 张伟明 on 2016/12/5.
 */

public class RestClient {

    public static final String webServer = "http://139.196.19.58:9191/redEnvelopeFun/";

//    public static final String webServer = "http://192.168.100.152:8282/redEnvelopeFun/";

    private static RequestServes api;
    public static RequestServes api() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(webServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(RequestServes.class);
        }
        return api;
    }


}
