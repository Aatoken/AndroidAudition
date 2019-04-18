package com.imooc.audition.delegates.photoview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.imooc.audition.R;
import com.imooc.latte_core.delegates.LatteDelegate;

/**
 * Author Aatoken
 * Date 2019/4/18 22:33
 * Description
 */
public class PhotoViewDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_photoview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        PhotoView photoView = (PhotoView) $(R.id.photo_view);
        photoView.setImageResource(R.mipmap.timg);
    }
}
