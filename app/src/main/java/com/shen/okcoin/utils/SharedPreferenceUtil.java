package com.shen.okcoin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.shen.okcoin.App;
import com.shen.okcoin.Constants;


public class SharedPreferenceUtil {
    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 引导页
     *
     * @return
     */
    public static boolean getGuideFlag() {
        return getAppSp().getBoolean(Constants.SP_GUIDE, true);
    }

    public static void setGudieFlag(boolean flag) {
        getAppSp().edit().putBoolean(Constants.SP_GUIDE, flag).apply();
    }

}
