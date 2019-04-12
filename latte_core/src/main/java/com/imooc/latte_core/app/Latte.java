package com.imooc.latte_core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Author Aatoken
 * Date 2019/4/12 17:16
 * Description APP全局调用配置项的入口
 */
public final class Latte {


    /**
     * 初始化配置
     * @return
     */
    public static Configurator init() {

        return getConfigurator();
    }

    /**
     * 外部调用
     * @return
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    private static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Context getContext() {
        return (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER.name());
    }

}
