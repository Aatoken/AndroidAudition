package com.imooc.latte_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Author Aatoken
 * Date 2019/4/14 17:12
 * Description
 */
public class ItemBuilder {

    /**
     * fragment队友的 icon ,title 集合
     */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 创建实例
     * @return
     */
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    /**
     * 填充数据
     * @param bean
     * @param delegate
     * @return
     */
    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    /**
     * 填充数据集
     * @param items
     * @return
     */
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    /**
     * 返回集合
     * @return
     */
    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }


}
