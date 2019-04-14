package com.imooc.latte_core.delegates.bottom;

/**
 * Author Aatoken
 * Date 2019/4/14 17:00
 * Description Tab的实体模型
 */
public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;


    public BottomTabBean(CharSequence icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
