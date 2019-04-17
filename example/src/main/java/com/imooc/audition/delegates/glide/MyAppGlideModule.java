package com.imooc.audition.delegates.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Author Aatoken
 * Date 2019/4/17 13:03
 * Description glide 自定义模块
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule{


    //硬盘缓存大小
    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;


    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }



}
