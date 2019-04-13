package com.imooc.audition.application;

import android.app.Application;

import com.imooc.audition.R;
import com.imooc.latte_core.app.Latte;
import com.imooc.latte_core.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


/**
 * Author Aatoken
 * Date 2019/4/12 16:55
 * Description
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init()
                .withContext(this)
                            //http://192.168.96.2:1314/RestServer/api/index.php
                .withApiHost("http://192.168.96.2:1314/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();
    }
}
