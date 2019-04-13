package com.imooc.latte_core.net.download;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.imooc.latte_core.net.RestCreator;
import com.imooc.latte_core.net.callback.IError;
import com.imooc.latte_core.net.callback.IFailure;
import com.imooc.latte_core.net.callback.IRequest;
import com.imooc.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author Aatoken
 * Date 2019/4/13 9:44
 * Description 下载
 */
public class DownLoadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownLoadHandler(String Url,
                           WeakHashMap<String, Object> Params,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;

    }

    public final void handlerDownLoad() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody>
                            response) {

                        if(response.isSuccessful())
                        {
                            //获取请求体
                            final ResponseBody responseBody=response.body();
                            //进行处理
                            final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR,EXTENSION,NAME,responseBody);

                            //这里一定要注意判断，否则文件下载不全
                            if(task.isCancelled())
                            {
                                if(REQUEST!=null)
                                {
                                    REQUEST.onRequestEnd();
                                }
                            }

                        }else {

                            if(ERROR!=null)
                            {
                                ERROR.onError(response.code(),response.message());
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE!=null)
                        {
                            FAILURE.onFailure();
                        }
                    }
                });


    }
}
