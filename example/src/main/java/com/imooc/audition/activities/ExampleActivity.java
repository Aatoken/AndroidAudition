package com.imooc.audition.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}