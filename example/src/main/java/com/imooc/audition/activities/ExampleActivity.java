package com.imooc.audition.activities;

import com.imooc.audition.delegates.photoview.PhotoViewDelegate;
import com.imooc.latte_core.activities.ProxyActivity;
import com.imooc.latte_core.delegates.LatteDelegate;

/**
 * Author Aatoken
 * Date 2019/4/12 16:54
 * Description
 */
public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        //return new EcBottomDelegate();
        //return new GlideDelegate();
        return new PhotoViewDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }
}