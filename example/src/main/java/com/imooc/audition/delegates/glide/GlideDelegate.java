package com.imooc.audition.delegates.glide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imooc.audition.R;
import com.imooc.latte_core.app.Latte;
import com.imooc.latte_core.delegates.LatteDelegate;
import com.imooc.latte_core.util.log.LatteLogger;

/**
 * Author Aatoken
 * Date 2019/4/15 10:23
 * Description glide的fragment
 */
public class GlideDelegate extends LatteDelegate implements View.OnClickListener {


    //ui
    private Button mBtn_img_loader = null;
    private ImageView mImgview_show = null;




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

                String url = Latte.getApiHost()+"img.png";
                Glide.with(this).load(url).into(mImgview_show);

                LatteLogger.w("GlideDelegate","点击图片加载");
                break;
            default:
                break;
        }
    }


}
