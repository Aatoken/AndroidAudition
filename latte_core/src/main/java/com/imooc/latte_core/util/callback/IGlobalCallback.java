package com.imooc.latte_core.util.callback;

import android.support.annotation.Nullable;

/**
 * Author Aatoken
 * Date 2019/4/14 8:38
 * Description  定义接口回调的
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);

}
