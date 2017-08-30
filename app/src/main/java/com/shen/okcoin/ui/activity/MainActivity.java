package com.shen.okcoin.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleActivity;
import com.shen.okcoin.ui.fragment.FuturesFragment;
import com.shen.okcoin.ui.fragment.HomeFragment;
import com.shen.okcoin.ui.fragment.MeFragment;
import com.shen.okcoin.ui.fragment.QuotesFragment;
import com.shen.okcoin.ui.fragment.TradingFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;

public class MainActivity extends SimpleActivity {
    @BindView(R.id.vp_content)
    SViewPager mVpContent;
    @BindView(R.id.fiv_function)
    FixedIndicatorView mFivFunction;

    private IndicatorViewPager mIndicatorViewPager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupView() {
        mIndicatorViewPager = new IndicatorViewPager(mFivFunction, mVpContent);
        mIndicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mFivFunction.setOnTransitionListener(new OnTransitionTextListener().setColor(
                ContextCompat.getColor(mContext, R.color.colorPrimary), Color.GRAY));
        // 禁止viewpager的滑动事件
        mVpContent.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        mVpContent.setOffscreenPageLimit(5);
    }

    private class MyAdapter extends IndicatorFragmentPagerAdapter {
        private int[] mTabNames = {R.string.tab_home, R.string.tab_trading, R.string.tab_quotes, R.string.tab_futures, R.string.tab_me};
        private int[] mTabIcons = {R.drawable.tab_home, R.drawable.tab_trading, R.drawable.tab_quotes,
                R.drawable.tab_futures, R.drawable.tab_me};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_tab, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mTabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, mTabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            if (position == 0) {
                return new HomeFragment();
            } else if (position == 1) {
                return new QuotesFragment();
            } else if (position == 2) {
                return new TradingFragment();
            } else if (position == 3) {
                return new FuturesFragment();
            } else if (position == 4) {
                return new MeFragment();
            }
            return null;
        }
    }
}
