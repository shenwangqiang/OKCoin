package com.shen.okcoin.ui.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleActivity;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shenwangqiang on 2017/8/31.
 */
public class GudieActivity extends SimpleActivity {
    @BindView(R.id.vp_gudie)
    ViewPager mVpGudie;
    @BindView(R.id.fiv_indicator)
    FixedIndicatorView mFivIndicator;
    @BindView(R.id.tv_enter)
    TextView mTvEnter;

    private IndicatorViewPager mIndicatorViewPager;

    private int[] mImages = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_gudie;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupView() {

        mIndicatorViewPager = new IndicatorViewPager(mFivIndicator, mVpGudie);
        mIndicatorViewPager.setAdapter(adapter);
        mVpGudie.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    mTvEnter.setVisibility(position==mImages.length-1?View.VISIBLE:View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_indicator, container, false);
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            convertView.setBackgroundResource(mImages[position]);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mImages.length;
        }
    };

    @OnClick(R.id.tv_enter)
    public void onViewClicked() {
        startActivity(new Intent(mContext,MainActivity.class));
        finish();
    }
}
