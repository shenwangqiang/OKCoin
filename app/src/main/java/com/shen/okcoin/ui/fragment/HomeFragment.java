package com.shen.okcoin.ui.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.shen.okcoin.Constants;
import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleFragment;
import com.shen.okcoin.http.business.ticker.TickerLoader;
import com.shen.okcoin.model.entity.Ticker;
import com.shen.okcoin.ui.adapter.QuoteAdapter;
import com.shen.okcoin.utils.CommonUtil;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class HomeFragment extends SimpleFragment {

    @BindView(R.id.vp_banner)
    ViewPager mVpBanner;
    @BindView(R.id.fiv_indicator)
    FixedIndicatorView mFivIndicator;
    @BindView(R.id.rv_quote)
    RecyclerView mRvQuote;

    private BannerComponent mBannerComponent;
    private String[] mBannerImgs = {Constants.BANNER_IMG_1, Constants.BANNER_IMG_2,
            Constants.BANNER_IMG_3, Constants.BANNER_IMG_4};

    private LinearLayoutManager mLayoutManager;
    private QuoteAdapter mQuoteAdapter;
    private ArrayList<Ticker.TickerBean> mTickerBeen = new ArrayList<>();

    private TickerLoader mTickerLoader;
    private Timer mTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mTickerLoader  = new TickerLoader();
        for(String str:Constants.CNY_LIST){
            mTickerBeen.add(new Ticker.TickerBean());
        }
        Ticker.TickerBean more = new Ticker.TickerBean();
        more.setLast("");
        more.setName(getString(R.string.more_quote));
        mTickerBeen.add(more);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(String str:Constants.CNY_LIST){
                    getTicker(str);
                }
            }
        }, 0, 2000);
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

        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRvQuote.setLayoutManager(mLayoutManager);
//        mRvQuote.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        mRvQuote.setHasFixedSize(true);
        mQuoteAdapter = new QuoteAdapter(mContext, mTickerBeen);
        mRvQuote.setAdapter(mQuoteAdapter);
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTimer.cancel();
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
            CommonUtil.loadImage(mContext, mBannerImgs[position], imageView, R.drawable.bg_welcome);
            return convertView;
        }

        @Override
        public int getCount() {
            return mBannerImgs.length;
        }
    };

    private void getTicker(final String cny){
        mTickerLoader.getTicker(cny).subscribe(new Action1<Ticker>() {
            @Override
            public void call(Ticker ticker) {
                int position = Constants.CNY_LIST.indexOf(cny);
                ticker.getTicker().setCny(cny);
                ticker.getTicker().setName(Constants.CNY_MAP.get(cny));
                mTickerBeen.set(position,ticker.getTicker());
                mQuoteAdapter.notifyItemChanged(position);

                EventBus.getDefault().post(ticker);

//                mQuoteAdapter.notifyItemChanged(position,ticker);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                CommonUtil.httpError(throwable);
            }
        });
    }
}
