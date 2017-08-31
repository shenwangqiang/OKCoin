package com.shen.okcoin.http.business.ticker;


import com.shen.okcoin.http.BaseLoad;
import com.shen.okcoin.http.ObjectLoader;
import com.shen.okcoin.http.RetrofitServiceManager;
import com.shen.okcoin.model.entity.Ticker;

import rx.Observable;

public class TickerLoader extends ObjectLoader {
    private TickerService mTickerService;

    public TickerLoader() {
        mTickerService = RetrofitServiceManager.getInstance().create(TickerService.class);
    }

    /**
     * 获取OKCoin最新市场行情数据
     * @param symbol
     * @return
     */
    public Observable<Ticker> getTicker(String symbol) {
        return observe(mTickerService.getTicker(symbol))
                .map(new BaseLoad<Ticker>());
    }
}
