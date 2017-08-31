package com.shen.okcoin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleActivity;

public class SplashActivity extends SimpleActivity {
    private boolean mFlag = true;
    @Override
    protected void onCreate(Bundle mBundle) {
        super.onCreate(mBundle);
    }

    @Override
    protected int getLayout() {
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
                startActivity(new Intent(mContext,mFlag?GudieActivity.class:MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
