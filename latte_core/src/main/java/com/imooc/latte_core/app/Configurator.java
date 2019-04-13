package com.imooc.latte_core.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Author Aatoken
 * Date 2019/4/12 17:18
 * Description  存储配置集合
 */
public class Configurator {

    /**
     * 所有配置项的集合
     */
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    /**
     * Handler
     */
    private static final Handler HANDLER = new Handler();
    /**
     * Icon 字体图标库
     */
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    /**
     * 拦截器集合
     */
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();

    /**
     * 进行初始化的操作
     */
    private Configurator() {
        //设置是否完成初始化
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取配置集合
     * @return
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }




    /**
     * LATTE_CONFIGS 存入数据
     * @param key
     * @param value
     */
    final  void putConfiguration(Object key,Object value) {
        LATTE_CONFIGS.put(key,value);
    }

    /**
     * 初始化字体库
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 添加字体图标库
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 添加上下文到集合中去
     * @param context
     * @return
     */
    public final Configurator withContext(Context context)
    {
        putConfiguration(ConfigKeys.APPLICATION_CONTEXT.name(),context);
        return this;
    }

    /**
     * 添加activity到配置集合中
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        putConfiguration(ConfigKeys.ACTIVITY.name(), activity);
        return this;
    }

    /**
     * 添加服务器host到集合中
     * @param host
     * @return
     */
    public Configurator withApiHost(String host) {
        putConfiguration(ConfigKeys.API_HOST.name(), host);
        return this;
    }


    /**
     * 添加拦截器
     * @param interceptor 拦截器
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }


    /**
     * 添加拦截器
     * @param interceptors 拦截器
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }


    /**
     * 检查是否完成初始化避免空指针调用
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }



    /**
     * 根据key获取相对于的Value
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }


    /**
     * 配置完成后调用此方法
     */
    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

}
