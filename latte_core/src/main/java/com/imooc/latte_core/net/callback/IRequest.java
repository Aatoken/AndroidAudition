package com.imooc.latte_core.net.callback;

/**
 * Author Aatoken
 * Date 2019/4/13 8:55
 * Description 请求开始于结束的回调
 */
public interface IRequest {
    void onRequestStart();//请求开始 用于做一些初始化
    void onRequestEnd();//请求结束
}
