package com.imooc.audition.delegates.glide;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.imooc.audition.R;
import com.imooc.latte_core.app.Latte;
import com.imooc.latte_core.delegates.LatteDelegate;
import com.imooc.latte_core.util.log.LatteLogger;
import com.imooc.latte_core.util.toast.LatteToast;

/**
 * Author Aatoken
 * Date 2019/4/15 10:23
 * Description glide的fragment
 */
public class GlideDelegate extends LatteDelegate implements View.OnClickListener {


    //ui
    private Button mBtn_img_loader = null;
    private ImageView mImgview_show = null;


    RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher)//加载成功之前占位图
            .error(R.mipmap.error)//加载错误之后的错误图
            .override(400, 400)//指定图片的尺寸//指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
            .fitCenter()//指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示。）
            .centerCrop()
            .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
            .skipMemoryCache(true)//跳过内存缓存
            .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存所有版本的图像
            .diskCacheStrategy(DiskCacheStrategy.NONE)//跳过磁盘缓存
            .diskCacheStrategy(DiskCacheStrategy.DATA)//只缓存原来分辨率的图片
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//只缓存最终的图片
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);//智能选择

    @Override
    public Object setLayout() {
        return R.layout.delegate_glide;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mBtn_img_loader = $(R.id.img_loader);
        mImgview_show = $(R.id.image_view);

        mBtn_img_loader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_loader:

                String url = Latte.getApiHost() + "img.png";
                Glide.with(this)
                        .load(url)
                        .apply(options)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                LatteToast.showToast("listener 成功！");
                                return false;
                            }
                        })
                        .into(mImgview_show);

                LatteLogger.w("GlideDelegate", "点击图片加载");
                break;
            default:
                break;
        }
    }




}
