package com.imooc.audition.application;

import android.app.Application;

import com.imooc.latte_core.app.Latte;
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
                .withIcon(new FontAwesomeModule())

                .configure();
    }
}
