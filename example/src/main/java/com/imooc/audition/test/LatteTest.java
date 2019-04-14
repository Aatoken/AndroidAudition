package com.imooc.audition.test;

import android.content.Context;
import android.support.annotation.Nullable;

import com.imooc.latte_core.net.RestClient;
import com.imooc.latte_core.net.callback.IFailure;
import com.imooc.latte_core.net.callback.ISuccess;
import com.imooc.latte_core.util.callback.CallbackManager;
import com.imooc.latte_core.util.callback.CallbackType;
import com.imooc.latte_core.util.callback.IGlobalCallback;
import com.imooc.latte_core.util.toast.LatteToast;

import static com.imooc.latte_core.app.Latte.getContext;

/**
 * Author Aatoken
 * Date 2019/4/14 9:23
 * Description
 */
public class LatteTest {

    private LatteTest() {
    }

    private static class Holder {
        private static final LatteTest INSTANCE = new LatteTest();
    }

    public static LatteTest getInstance() {
        return Holder.INSTANCE;
    }


    /**
     * 测试接口回调
     */
    public void testCallBack() {
        CallbackManager.getInstance().addCallback(CallbackType.TEST, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                LatteToast.showToast(args+"OK");
            }
        });

        CallbackManager.getInstance().getCallback(CallbackType.TEST).executeCallback("测试接口回调");
    }

    /**
     * 测试网络
     * @param context
     */
    public void testNet(Context context) {
        RestClient.builder()
                .url("index.php")
                .loader(context)
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
