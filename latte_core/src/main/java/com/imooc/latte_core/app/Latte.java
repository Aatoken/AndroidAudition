package com.imooc.latte_core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;

import okhttp3.Interceptor;

/**
 * Author Aatoken
 * Date 2019/4/12 17:16
 * Description APP全局调用配置项的入口
 */
public final class Latte {


    /**
     * 初始化配置
     *
     * @return
     */
    public static Configurator init() {

        return getConfigurator();
    }

    /**
     * 外部调用
     *
     * @return
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }


    /**
     * 根据key获取在配置中的value
     * @param key
     * @param <T>
     * @return
     */
    private static <T> T getConfigValueByKey(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取初始化配置中的 ApplicationContext
     * @return
     */
    public static Application getApplicationContext() {
        return getConfigValueByKey(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    /**
     *获取初始化配置中的 Context
     * @return
     */
    public static Context getContext() {
        return (Context) getConfigValueByKey(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    /**
     *获取初始化配置中的 Handler
     * @return
     */
    public static Handler getHandler() {
        return getConfigValueByKey(ConfigKeys.HANDLER.name());
    }

    /**
     * 获取初始化配置中的 Interceptor
     * @return
     */
    public static Interceptor getInterceptor() {
        return getConfigValueByKey(ConfigKeys.INTERCEPTOR.name());
    }

    /**
     * 获取初始化配置中的 集合 Interceptor
     * @return
     */
    public static ArrayList<Interceptor> getInterceptors() {
        return getConfigValueByKey(ConfigKeys.INTERCEPTOR.name());
    }

    public static String getApiHost()
    {
        return getConfigValueByKey(ConfigKeys.API_HOST.name());
    }



}
