package com.shen.okcoin.ui.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shen.okcoin.Constans;
import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleFragment;
import com.shen.okcoin.utils.CommonUtil;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

import butterknife.BindView;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class HomeFragment extends SimpleFragment {

    @BindView(R.id.vp_banner)
    ViewPager mVpBanner;
    @BindView(R.id.fiv_indicator)
    FixedIndicatorView mFivIndicator;

    private BannerComponent mBannerComponent;
    private String[] mBannerImgs = {Constans.BANNER_IMG_1, Constans.BANNER_IMG_2,
            Constans.BANNER_IMG_3, Constans.BANNER_IMG_4};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupView() {
        mFivIndicator.setScrollBar(new ColorBar(mContext, Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        mVpBanner.setOffscreenPageLimit(2);

        mBannerComponent = new BannerComponent(mFivIndicator, mVpBanner, false);
        mBannerComponent.setAdapter(adapter);

        //默认就是800毫秒，设置单页滑动效果的时间
        //bannerComponent.setScrollDuration(800);
        //设置播放间隔时间，默认情况是3000毫秒
        mBannerComponent.setAutoPlayTime(2500);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerComponent.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerComponent.stopAutoPlay();
    }


    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(container.getContext());
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new ImageView(mContext);
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            CommonUtil.loadImage(mContext,mBannerImgs[position],imageView,R.drawable.bg_welcome);
            return convertView;
        }

        @Override
        public int getCount() {
            return mBannerImgs.length;
        }
    };
}
