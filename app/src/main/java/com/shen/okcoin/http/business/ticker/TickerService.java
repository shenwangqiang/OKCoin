package com.shen.okcoin.http.business.ticker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shen.okcoin.model.entity.Line;
import com.shen.okcoin.model.entity.Ticker;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TickerService {
    /**
     * 获取OKCoin最新市场行情数据
     *
     * @param symbol btc_cny：比特币    ltc_cny：莱特币    eth_cny :以太坊     etc_cny :以太经典    bcc_cny :比特现金
     * @return
     */
    @GET("api/v1/ticker.do")
    Observable<Ticker> getTicker(@Query("symbol") String symbol);

    /**
     * 获取OKCoin市场深度
     *
     * @param symbol
     * @param size   （否）默认200  value: 1-200
     * @param merge  （否）默认返回0.01深度  合并深度: 1, 0.1
     * @return
     */
    @GET("api/v1/depth.do")
    Observable<Ticker> getDepth(@Query("symbol") String symbol, @Query("size") int size, @Query("merge") int merge);

    /**
     * 获取OKCoin交易信息(600条)
     *
     * @param symbol
     * @param since  否(默认返回最近成交600条)  tid:交易记录ID（返回数据不包括当前tid值,最多返回600条数据）
     * @return
     */
    @GET("api/v1/trades.do")
    Observable<Ticker> getTrades(@Query("symbol") String symbol, @Query("since") long since);


    /**
     * 获取OKCoin的K线数据
     *
     * @param symbol
     * @param type   是
     *               1min : 1分钟
     *               3min : 3分钟
     *               5min : 5分钟
     *               15min : 15分钟
     *               30min : 30分钟
     *               1day : 1日
     *               3day : 3日
     *               1week : 1周
     *               1hour : 1小时
     *               2hour : 2小时
     *               4hour : 4小时
     *               6hour : 6小时
     *               12hour : 12小时
     * @param size   否(默认全部获取)  指定获取数据的条数
     * @param since  否(默认全部获取)  时间戳，返回该时间戳以后的数据(例如1417536000000)
     * @return
     */
    @GET("api/v1/kline.do")
    Observable<ArrayList<String[]>> getKLine(@Query("symbol") String symbol, @Query("type") String type,
                                   @Query("size") int size, @Query("since") long since);

}
