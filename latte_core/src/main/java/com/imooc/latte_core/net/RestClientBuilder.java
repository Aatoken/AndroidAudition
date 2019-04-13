package com.imooc.latte_core.net;

import android.content.Context;

import com.imooc.latte_core.net.callback.IError;
import com.imooc.latte_core.net.callback.IFailure;
import com.imooc.latte_core.net.callback.IRequest;
import com.imooc.latte_core.net.callback.ISuccess;
import com.imooc.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Author Aatoken
 * Date 2019/4/13 8:47
 * Description 用于构建RestClient的初始化参数
 */
public final class RestClientBuilder {

    /**
     * 使用final 必须给赋值
     */
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;

    /**
     * 添加Url
     *
     * @param url
     * @return
     */
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 添加请求参数集合 弱引用
     *
     * @param params
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 添加请求
     *
     * @param request
     * @return
     */
    public final RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    /**
     * 添加回调--请求成功
     *
     * @param success
     * @return
     */
    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }


    /**
     * 添加回调--请求失败
     *
     * @param failure
     * @return
     */
    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    /**
     * 重载 添加请求请求参数
     *
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 添加回调--请求失败
     *
     * @param error
     * @return
     */
    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    /**
     * 添加请求体
     *
     * @param body
     * @return
     */
    public final RestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    /**
     * 添加加载显示的style
     *
     * @param context
     * @param style
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mLoaderStyle = style;
        this.mContext = context;
        return this;
    }

    /**
     * 重载 添加加载显示的默认样式
     *
     * @param context
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallTrianglePathIndicator;
        this.mContext = context;
        return this;
    }

    /**
     * 添加下载文件
     *
     * @param file
     * @return
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 添加下载文件的路径
     *
     * @param filePath
     * @return
     */
    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    /**
     * 添加文件目录
     *
     * @param dir
     * @return
     */
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 文件的后缀名
     *
     * @param extension
     * @return
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 添加下载文件的完整名称
     *
     * @param name
     * @return
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    /**
     * 完成参数的配置
     *
     * @return
     */
    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mIRequest, mISuccess, mIFailure, mIError,
                mBody, mFile,
                mDownloadDir, mExtension, mName,
                mLoaderStyle, mContext);
    }


}
