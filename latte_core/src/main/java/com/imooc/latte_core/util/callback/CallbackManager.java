package com.imooc.latte_core.util.callback;

import java.util.WeakHashMap;

/**
 * Author Aatoken
 * Date 2019/4/14 8:39
 * Description 接口回调的入口，处理不同的类型回调，先注册，在执行
 */
public class CallbackManager {

    /**
     * 弱引用 callback的集合
     */
    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();


    private CallbackManager() {
    }

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 注册一个接口回调
     * @param tag
     * @param callback
     * @return
     */
    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    /**
     * 获取对应的接口回调，然后执行
     * @param tag
     * @return
     */
    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

}
