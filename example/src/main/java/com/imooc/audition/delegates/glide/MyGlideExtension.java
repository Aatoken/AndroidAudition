package com.imooc.audition.delegates.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.imooc.audition.R;

/**
 * Author Aatoken
 * Date 2019/4/17 13:26
 * Description 定义自己的 glide api
 */

@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }
    @GlideOption
    public static void cacheSource(RequestOptions options) {
        options.diskCacheStrategy(DiskCacheStrategy.DATA);

    }
}
