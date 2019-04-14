package com.imooc.audition.delegates.bottom.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.imooc.audition.R;
import com.imooc.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Author Aatoken
 * Date 2019/4/14 22:52
 * Description 购物车
 */
public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}