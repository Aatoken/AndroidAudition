package com.imooc.latte_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.imooc.latte_core.R;
import com.imooc.latte_core.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Author Aatoken
 * Date 2019/4/14 17:00
 * Description
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    /**
     * 创建tab集合
     */
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    /**
     * item 的fragment
     */
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    /**
     * items
     */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    //初始化参数
    private int mCurrentDelegate = 0;//当前的fragment
    private int mIndexDelegate = 0;//默认的索引
    private int mClickedColor = Color.RED;//点击之后的颜色,默认为红色

    private LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    /**
     * 填充数据 items
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    /**
     * 填充 fragment
     *
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 填充点击之后的颜色 必须是 colorInt
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    /**
     * 重置颜色
     */
    private void resetColor() {
        //获取tab的个数
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            //获取 子布局的 RelativeLayout
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //获取子布局中的数据
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    /**
     * 颜色的切换
     *
     * @param tabIndex 当前的索引
     */
    public void changeColor(int tabIndex) {
        //清空
        resetColor();
        //获取当前所在 布局
        final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(tabIndex);
        //修改子控件的颜色
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
    }


    @Override
    public void onClick(View v) {
        final int tabIndex = (int) v.getTag();
        changeColor(tabIndex);
        //注意先后顺序
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tabIndex), ITEM_DELEGATES.get(mCurrentDelegate));

        mCurrentDelegate = tabIndex;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fragment
        mIndexDelegate = setIndexDelegate();
        //获取颜色
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mBottomBar = $(R.id.bottom_bar);
        final int size = ITEMS.size();
        //给 item 添加布局
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            //设置默认的索引位置
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        //设置加载样式类似于微信
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }



}
