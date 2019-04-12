package com.imooc.audition.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.imooc.audition.R;
import com.imooc.latte_core.delegates.LatteDelegate;

/**
 * Author Aatoken
 * Date 2019/4/12 17:11
 * Description
 */
public class ExampleDelegate  extends LatteDelegate {
    @Override
    public Object setLayout() {
        return  R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
