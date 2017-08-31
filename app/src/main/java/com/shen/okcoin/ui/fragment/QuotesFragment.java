package com.shen.okcoin.ui.fragment;

import android.support.design.widget.TabLayout;
import com.shen.okcoin.Constants;
import com.shen.okcoin.R;
import com.shen.okcoin.base.SimpleFragment;
import com.shen.okcoin.http.business.ticker.TickerLoader;
import com.shen.okcoin.model.entity.Line;
import com.shen.okcoin.utils.CommonUtil;
import java.util.ArrayList;
import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class QuotesFragment extends SimpleFragment {
    @BindView(R.id.tl_category)
    TabLayout mTlCategory;

    private TickerLoader mTickerLoader;

    private ArrayList<Line> mLines = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quotes;
    }

    @Override
    protected void initData() {
        mTickerLoader = new TickerLoader();
        getTicker("btc_cny");
    }

    @Override
    protected void setupView() {

    }

    private void getTicker(final String cny){
        long since = System.currentTimeMillis()-Constants.HOUR_Millis;
        mTickerLoader.getKLine(cny,"1min",60,since).subscribe(new Action1<ArrayList<String[]>>() {
            @Override
            public void call(ArrayList<String[]> result) {
                for(String[] array:result){
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
