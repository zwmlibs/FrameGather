package com.zm.framegather.network;

import com.zm.framegather.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description 描述 基于Retrofit实现的网络请求
 * Retrofit与okhttp共同出自于Square公司，retrofit就是对okhttp做了一层封装。把网络请求都交给给了Okhttp，
 * 我们只需要通过简单的配置就能使用retrofit来进行网络请求了，其主要作者是Android大神JakeWharton。
 *
 * 导包
 * compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'//Retrofit2所需要的包
 * compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'//ConverterFactory的Gson依赖包
 * compile 'com.squareup.retrofit2:converter-scalars:2.0.0-beta4'//ConverterFactory的String依赖包
 * 这里需要值得注意的是导入的retrofit2包的版本必须要一致，否则就会报错。
 *
 * 与Okhttp不同的是，Retrofit需要定义一个接口，用来返回我们的Call对象，这里示范的是Post请求：
 *
 * Retrofit提供的请求方式注解有@GET和@POST，参数注解有@PATH和@Query等，我们只介绍常用的;前两个顾名思义就是定义
 * 你的请求方式Get or Post，后面的@PATH指的是通过参数填充完整的路径，
 *
 * Created by 张伟明 on 2016/12/5.
 */

public interface RequestServes {
    @POST("front/userAct.htm?operate=findUser")
        //这里@Query("loginname")就是键，后面的loginname就是具体的值了，
        // 值得注意的是Get和Post请求，都是这样填充参数的；
    Call<BaseRequest<UserBean>> getString(@Query("userId") String userId);
}
