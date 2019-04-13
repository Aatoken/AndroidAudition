package com.imooc.latte_core.net.callback;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.imooc.latte_core.ui.loader.LatteLoader;
import com.imooc.latte_core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author Aatoken
 * Date 2019/4/13 8:57
 * Description 用于处理回调
 */
public class RequestCallBacks implements Callback<String> {

    private final IRequest REQUEST;//请求体
    private final ISuccess SUCCESS;//回调--请求成功
    private final IFailure FAILURE;//回调--请求失败
    private final IError ERROR;//回调--请求错误
    private final LoaderStyle LOADER_STYLE;//回调--样式


    /**
     * Handler尽量声明成static，避免内存泄漏
     */
    private static final Handler HANDLER=new Handler();


    public RequestCallBacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=loaderStyle;
    }

    /**
     * 请求成功
     * @param call
     * @param response
     */
    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if(response.isSuccessful())
        {
            if(call.isExecuted())
            {
                if(SUCCESS!=null)
                {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR!=null)
            {
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();
    }

    /**
     * 请求失败
     * @param call
     * @param t
     */
    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if(FAILURE!=null)
        {
            FAILURE.onFailure();
        }

        if(REQUEST!=null)
        {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }


    /**
     * 停止加载
     */
    private void stopLoading()
    {
        //LOADER_STYLE
        if(LOADER_STYLE!=null)
        {
            //给加载 设置1S的延迟
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }

}
