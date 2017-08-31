package com.shen.okcoin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleActivity;
import com.shen.okcoin.utils.SharedPreferenceUtil;

public class SplashActivity extends SimpleActivity {
    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedPreferenceUtil.getGuideFlag()){
                    SharedPreferenceUtil.setGudieFlag(false);
                    startActivity(new Intent(mContext,GudieActivity.class));
                } else {
                    startActivity(new Intent(mContext,  MainActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}
