package com.shen.okcoin.http.business.ticker;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shen.okcoin.http.BaseLoad;
import com.shen.okcoin.http.ObjectLoader;
import com.shen.okcoin.http.RetrofitServiceManager;
import com.shen.okcoin.model.entity.Line;
import com.shen.okcoin.model.entity.Ticker;

import java.util.ArrayList;

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

    /**
     * 获取OKCoin最新市场行情数据
     * @param symbol
     * @return
     */
    public Observable<ArrayList<String[]>> getKLine(String symbol, String type, int size, long since) {
        return observe(mTickerService.getKLine(symbol,type,size,since))
                .map(new BaseLoad<ArrayList<String[]>>());
    }
}
