package com.imooc.latte_core.net.callback;

/**
 * Author Aatoken
 * Date 2019/4/13 8:53
 * Description 请求错误回调
 */
public interface IError {
    void onError(int code,String msg);
}
