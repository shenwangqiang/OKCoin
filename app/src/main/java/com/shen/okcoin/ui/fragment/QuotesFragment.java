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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
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
public class QuotesFragment extends SimpleFragment implements OnChartValueSelectedListener{
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
    @BindView(R.id.lc_line)
    LineChart mLcLine;

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

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
//                getTicker(mCNY);
                getKLine(mCNY);
            }
        }, 0, 60000);
        EventBus.getDefault().register(this);
    }

    @Subscriber
    private void refresh(Ticker ticker) {
        if (ticker.getTicker().getCny().equals(mCNY)) {
            refreshTicker(ticker.getTicker());
            if(mLines.size()>0) {
                String[] array = {
                        System.currentTimeMillis() + "",
                        "", "", "", ticker.getTicker().getLast(), ""
                };
                mLines.set(mLines.size() - 1, new Line(array));

                setData();
                mLcLine.invalidate();
            }
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

        mLcLine.setOnChartValueSelectedListener(this);

        // no description text
        mLcLine.getDescription().setEnabled(false);

        // enable touch gestures
        mLcLine.setTouchEnabled(true);

        mLcLine.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mLcLine.setDragEnabled(true);
        mLcLine.setScaleEnabled(true);
        mLcLine.setDrawGridBackground(false);
        mLcLine.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLcLine.setPinchZoom(true);

        // set an alternative background color
//        mLcLine.setBackgroundColor(Color.LTGRAY);

        // add data

        mLcLine.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mLcLine.getLegend();

        // modify the legend ...
//        l.setForm(Legend.LegendForm.LINE);
////        l.setTypeface(mTfLight);
//        l.setTextSize(11f);
//        l.setTextColor(Color.WHITE);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mLcLine.getXAxis();
//        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setEnabled(false);

        mLcLine.getAxisLeft().setLabelCount(0, false);


//        YAxis leftAxis = mLcLine.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
//        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
//        leftAxis.setAxisMaximum(200f);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mLcLine.getAxisRight();
//        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setEnabled(false);
    }

    private void setData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for(Line line:mLines){
            yVals1.add(new Entry(line.getTime(), line.getClose()));
        }

        LineDataSet set1;

        if (mLcLine.getData() != null &&
                mLcLine.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLcLine.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mLcLine.getData().notifyDataChanged();
            mLcLine.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.WHITE);
            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            mLcLine.setData(data);
        }
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

    private void refreshTicker(Ticker.TickerBean bean) {
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
        mTickerLoader.getKLine(cny, "3min", 60, since).subscribe(new Action1<ArrayList<String[]>>() {
            @Override
            public void call(ArrayList<String[]> result) {
                mLines.clear();

                for (String[] array : result) {
                    mLines.add(new Line(array));
                }
                setData();
                mLcLine.invalidate();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                CommonUtil.httpError(throwable);
            }
        });
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
