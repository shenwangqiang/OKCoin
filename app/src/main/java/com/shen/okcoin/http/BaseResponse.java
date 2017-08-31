package com.shen.okcoin.http;

/**
 * 网络请求结果 基类
 */

public class BaseResponse<T> {
    public int errcode;
    public String errmsg;
    public String code;

    public T data;

    public boolean isSuccess() {
        return errcode == 0;
    }
}
