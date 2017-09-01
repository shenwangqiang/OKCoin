package com.shen.okcoin.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shen.okcoin.Constants;
import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleFragment;
import com.shen.okcoin.http.business.ticker.TickerLoader;
import com.shen.okcoin.model.entity.Line;
import com.shen.okcoin.model.entity.Ticker;
import com.shen.okcoin.utils.CommonUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class QuotesFragment extends SimpleFragment {
    @BindView(R.id.tl_category)
    TabLayout mTlCategory;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_volume)
    TextView mTvVolume;
    @BindView(R.id.tv_symbol)
    TextView mTvSymbol;
    @BindView(R.id.tv_price)
    TextView mTvPrice;

    private TickerLoader mTickerLoader;

    /**
     * 上一次价格
     */
    private float mLastPrice;
    private Drawable mUp, mDown;

    private String mCNY = Constants.CNY_LIST.get(0);
    private Timer mTimer;

    private ArrayList<Line> mLines = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quotes;
    }

    @Override
    protected void initData() {
        mUp = ContextCompat.getDrawable(mContext, R.drawable.ic_up);
        mDown = ContextCompat.getDrawable(mContext, R.drawable.ic_down);

        mTickerLoader = new TickerLoader();

//        mTimer = new Timer();
//        mTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                getTicker(mCNY);
//                getKLine(mCNY);
//            }
//        }, 0, 2000);
        EventBus.getDefault().register(this);
    }

    @Subscriber
    private void refresh(Ticker ticker) {
        if(ticker.getTicker().getCny().equals(mCNY)) {
            refreshTicker(ticker.getTicker());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void setupView() {
        mTlCategory.addTab(mTlCategory.newTab().setText(Constants.NAME_LIST.get(0)));
        mTlCategory.addTab(mTlCategory.newTab().setText(Constants.NAME_LIST.get(1)));
        mTlCategory.addTab(mTlCategory.newTab().setText(Constants.NAME_LIST.get(2)));
        mTlCategory.addTab(mTlCategory.newTab().setText(Constants.NAME_LIST.get(3)));

        mTlCategory.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mLastPrice = 0;
                mCNY = Constants.CNY_LIST.get(tab.getPosition());
                getTicker(mCNY);
                getKLine(mCNY);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getTicker(final String cny) {
        mTickerLoader.getTicker(cny).subscribe(new Action1<Ticker>() {
            @Override
            public void call(Ticker ticker) {
                refreshTicker(ticker.getTicker());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                CommonUtil.httpError(throwable);
            }
        });
    }

    private void refreshTicker(Ticker.TickerBean bean){
        mTvVolume.setText(getString(R.string.volume,
                CommonUtil.getNumStr((int) Float.parseFloat(bean.getVol()) + "")));
        float last = Float.parseFloat(bean.getLast());
        Drawable status = null;
        if (mLastPrice == 0 || mLastPrice == last) {
            mTvPrice.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            mTvPrice.setTextColor(last > mLastPrice ? Color.GREEN : Color.RED);
            status = last > mLastPrice ? mUp : mDown;
        }
        mTvPrice.setCompoundDrawablesWithIntrinsicBounds(status, null, null, null);
        mLastPrice = last;
        mTvPrice.setText(bean.getLast());
    }

    private void getKLine(final String cny) {
        long since = System.currentTimeMillis() - Constants.HOUR_Millis;
        mTickerLoader.getKLine(cny, "1min", 60, since).subscribe(new Action1<ArrayList<String[]>>() {
            @Override
            public void call(ArrayList<String[]> result) {
                for (String[] array : result) {
                    mLines.add(new Line(array));
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                CommonUtil.httpError(throwable);
            }
        });
    }
}
