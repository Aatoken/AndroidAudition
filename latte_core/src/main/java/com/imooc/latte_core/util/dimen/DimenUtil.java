package com.imooc.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.imooc.latte_core.app.Latte;

/**
 * Author Aatoken
 * Date 2019/4/13 10:09
 * Description 测量工具
 */
public class DimenUtil {

    /**
     * 获取屏幕的宽
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }



}
