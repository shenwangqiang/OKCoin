package com.shen.okcoin.http;

import rx.functions.Func1;

/**
 * 剥离 最终数据
 */

public class BaseLoad<T> implements Func1<T, T> {
    /**
     * 是否弹出提示
     */
    private boolean mShowMsg = false;

    public BaseLoad() {
    }

    public BaseLoad(boolean flag) {
        mShowMsg = flag;
    }

    @Override
    public T call(T t) {
        if (mShowMsg) {
//            ToastUtil.shortShow(tBaseResponse.errmsg);
        }
        if (t != null) {
            return t;
        } else {
            return (T) "";
        }
    }
}
