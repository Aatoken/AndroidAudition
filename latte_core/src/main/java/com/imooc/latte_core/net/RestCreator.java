package com.imooc.latte_core.net;


import com.imooc.latte_core.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author Aatoken
 * Date 2019/4/13 8:08
 * Description
 */
public final class RestCreator {

    /**
     * 使用静态内部类获取参数的初始化
     *
     * @return
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 静态内部类
     */
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * OkHttp的初始化
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getInterceptors();

        /**
         * 将所有从配置项中获取的拦截器添加到 okhttp
         *
         * @return
         */
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .followRedirects(true)//设置从定性
                .build();

    }

    /**
     * Retrofit初始化配置
     */
    private static final class RetrofitHolder {

        private static final String BASE_URI = Latte.getApiHost();
        /**
         * Retrofit的创建
         */
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                //String解析器
                .addConverterFactory(ScalarsConverterFactory.create())
                //Json解析器
                .addConverterFactory(GsonConverterFactory.create())
                //RxJava
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * RestService的实例
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }

    /**
     * 获取RestService
     *
     * @return
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


}
