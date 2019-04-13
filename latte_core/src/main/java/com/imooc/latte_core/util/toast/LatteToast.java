package com.imooc.latte_core.util.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.imooc.latte_core.app.Latte;

/**
 * Author Aatoken
 * Date 2019/4/13 9:50
 * Description 自定义吐司，重复使用
 */
public class LatteToast {


    private static Toast toast;


    @SuppressLint("ShowToast")
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(Latte.getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
