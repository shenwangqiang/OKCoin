package com.shen.okcoin.http;

import rx.functions.Func1;

/**
 * 剥离 最终数据
 */

public class PayLoad<T> implements Func1<BaseResponse<T>, T> {
    /**
     * 是否弹出提示
     */
    private boolean mShowMsg = false;

    public PayLoad() {
    }

    public PayLoad(boolean flag) {
        mShowMsg = flag;
    }

    @Override
    public T call(BaseResponse<T> tBaseResponse) {//获取数据失败时，包装一个Fault 抛给上层处理错误
        if (!tBaseResponse.isSuccess()) {
            throw new Fault(tBaseResponse.errcode, tBaseResponse.errmsg);
        }
        if (mShowMsg) {
//            ToastUtil.shortShow(tBaseResponse.errmsg);
        }
        if (tBaseResponse.data != null) {
            return tBaseResponse.data;
        } else {
            return (T) tBaseResponse.errmsg;
        }
    }
}
