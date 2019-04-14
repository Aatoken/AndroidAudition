package com.imooc.latte_core.ui.icon;

import com.joanzapata.iconify.Icon;

/**
 * Author Aatoken
 * Date 2019/4/14 9:18
 * Description
 */
public enum  EcIcons implements Icon {
    icon_scan('\ue602'),

    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}