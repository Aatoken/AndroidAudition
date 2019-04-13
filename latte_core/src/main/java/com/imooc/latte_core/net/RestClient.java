package com.imooc.latte_core.net;

import android.content.Context;

import com.imooc.latte_core.net.callback.IError;
import com.imooc.latte_core.net.callback.IFailure;
import com.imooc.latte_core.net.callback.IRequest;
import com.imooc.latte_core.net.callback.ISuccess;
import com.imooc.latte_core.net.callback.RequestCallBacks;
import com.imooc.latte_core.net.download.DownLoadHandler;
import com.imooc.latte_core.net.request.HttpMethod;
import com.imooc.latte_core.ui.loader.LatteLoader;
import com.imooc.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Author Aatoken
 * Date 2019/4/13 8:47
 * Description 使用建造者模式 1.构造参数 2.开始网路请求 3.回调处理
 */
public final class RestClient {

    /**
     * 使用final 必须给赋值
     */
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    /**
     * 初始化赋值
     *
     * @param Url         路径
     * @param Params      参数集合
     * @param request     请求
     * @param success     请求成功
     * @param failure     请求失败
     * @param error       请求错误
     * @param body        主体
     * @param file        文件
     * @param downloadDir 下载目录
     * @param extension   后缀名
     * @param name        下载的文件名
     * @param loaderStyle 加载控件样式
     * @param context     上下文
     */
    public RestClient(String Url,
                      WeakHashMap<String, Object> Params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extension,
                      String name,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = Url;
        PARAMS.putAll(Params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }


    /**
     * 使用builder构造参数
     *
     * @return
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * 请求方式--get
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     *请求方式--post
     */
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    /**
     * 请求方式--put
     */
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * 请求方式--delete
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    /**
     * 请求方式--update
     */
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    /**
     * 请求方式--download
     */
    public final void download() {
        //对下载的封装
        new DownLoadHandler(URL, PARAMS,
                REQUEST, SUCCESS, FAILURE, ERROR,
                DOWNLOAD_DIR, EXTENSION, NAME).handlerDownLoad();

    }


    /**
     * 开始网络请求
     */
    public void request(HttpMethod method) {
        //1.获取Okhttp服务
        final RestService service = RestCreator.getRestService();
        //2.回调
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        //开始加载
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        //根据不同的请求模式请求
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody
                        .FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE
                        .getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        //开始真正的请求，并且传入回调
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    /**
     * 返回回调
     * @return
     */
    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }




}
