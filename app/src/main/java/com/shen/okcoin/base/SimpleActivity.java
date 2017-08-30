package com.shen.okcoin.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.shen.okcoin.App;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class SimpleActivity extends SupportActivity {

    protected App mApp;
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;

        mApp = App.getInstance();
        mApp.addActivity(this);
        initData();
        setupView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置view
     */
    protected abstract void setupView();
}
