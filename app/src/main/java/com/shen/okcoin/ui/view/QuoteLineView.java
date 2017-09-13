package com.shen.okcoin.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.shen.okcoin.model.entity.Line;

import java.util.ArrayList;

public class QuoteLineView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ArrayList<Line> mDatas = new ArrayList<>();
    private float mHight, mLow;
    private int[] mArray = new int[5];

    private int mTimeHeight,mPriceWidth,mPriceLineWidth;

    public QuoteLineView(Context context) {
        super(context);
    }

    public QuoteLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QuoteLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(ArrayList<Line> list) {
        mDatas.clear();
        mDatas.addAll(list);
        initHightAndLow();
    }

    /**
     * 初始化获取最高价及最低价
     */
    public void initHightAndLow() {
        float close;
        for (Line line : mDatas) {
            close = line.getClose();
            if (mHight == 0) {
                mHight = close;
            } else if (close > mHight) {
                mHight = close;
            }
            if (mLow == 0) {
                mLow = close;
            } else if (close < mLow) {
                mLow = close;
            }
        }
        int pace = (int) (mHight - mLow) / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);

        canvas.drawLine(0, (height - 30) / 3, width - 120, (height - 30) / 3, mPaint);
        canvas.drawLine(0, (height - 30) / 3 * 2, width - 120, (height - 30) / 3 * 2, mPaint);
        mPaint.clearShadowLayer();
        canvas.drawLine(width - 120, 0, width - 120, height - 30, mPaint);
        canvas.drawLine(width - 120, (height - 30) / 3, width - 120+20, (height - 30) / 3, mPaint);
        canvas.drawLine(width - 120, (height - 30) / 3*2, width - 120+20, (height - 30) / 3*2, mPaint);
        canvas.drawLine(0, height - 30, width, height - 30, mPaint);
    }
}
