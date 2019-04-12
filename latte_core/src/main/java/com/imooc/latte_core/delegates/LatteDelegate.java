package com.imooc.latte_core.delegates;

/**
 * Author Aatoken
 * Date 2019/4/12 17:06
 * Description App内部调用的 fragment 的基类
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
