package com.shen.okcoin.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shen.okcoin.R;
import com.shen.okcoin.model.entity.Ticker;

import java.util.ArrayList;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class QuoteAdapter extends BaseQuickAdapter<Ticker.TickerBean, BaseViewHolder> {
    private Context mContext;

    public QuoteAdapter(Context context, ArrayList<Ticker.TickerBean> list) {
        super(R.layout.adapter_quote, list);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Ticker.TickerBean item) {
        boolean flag = !"".equals(item.getLast());
        helper.setText(R.id.tv_name, flag?mContext.getString(R.string.price,item.getName()):item.getName())
                .setText(R.id.tv_last, mContext.getString(R.string.rmb, item.getLast()))
                .setText(R.id.tv_count, item.getVol())
                .setVisible(R.id.tv_last,flag)
                .setVisible(R.id.tv_count,flag);
    }
}
