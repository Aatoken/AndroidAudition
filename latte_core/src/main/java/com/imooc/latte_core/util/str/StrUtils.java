package com.imooc.latte_core.util.str;

/**
 * Author Aatoken
 * Date 2019/4/13 9:49
 * Description 字符串的处理
 */
public class StrUtils {

    /**
     * 为空验证
     * @param str
     * @return true为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }
}
