package com.imooc.audition.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.imooc.audition.R;
import com.imooc.latte_core.delegates.LatteDelegate;
import com.imooc.latte_core.net.RestClient;
import com.imooc.latte_core.net.callback.IFailure;
import com.imooc.latte_core.net.callback.ISuccess;
import com.imooc.latte_core.util.toast.LatteToast;

/**
 * Author Aatoken
 * Date 2019/4/12 17:11
 * Description
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        RestClient.builder()
                .url("index.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteToast.showToast(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        LatteToast.showToast("失败");
                    }
                })
                .build()
                .get();
    }


}
