package com.shen.okcoin.http.business.trade;


import com.shen.okcoin.http.BaseLoad;
import com.shen.okcoin.http.ObjectLoader;
import com.shen.okcoin.http.RetrofitServiceManager;
import com.shen.okcoin.model.entity.Ticker;

import java.util.ArrayList;

import rx.Observable;

public class TradeLoader extends ObjectLoader {
    private TradeService mTradeService;

    public TradeLoader() {
        mTradeService = RetrofitServiceManager.getInstance().create(TradeService.class);
    }


//    public Observable<Ticker> getTicker(String symbol) {
//        return observe(mTradeService.getTicker(symbol))
//                .map(new BaseLoad<Ticker>());
//    }


}
