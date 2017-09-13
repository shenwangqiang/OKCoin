package com.shen.okcoin.http.business.trade;

import com.shen.okcoin.model.entity.Ticker;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TradeService {
//    URL https://www.okcoin.cn/api/v1/trade.do
//    api_key
//            String
//    是
//            用户申请的apiKey
//    symbol
//            String
//    是
//    btc_cny: 比特币    ltc_cny: 莱特币    eth_cny :以太坊     etc_cny :以太经典    bcc_cny :比特现金
//            type
//    String
//            是
//    买卖类型： 限价单（buy/sell） 市价单（buy_market/sell_market）
//    price
//            Double
//    否
//    下单价格 [限价买单(必填)： 大于等于0，小于等于1000000 | 市价买单(必填)： BTC :最少买入0.01个BTC 的金额(金额>0.01*卖一价) / LTC :最少买入0.1个LTC 的金额(金额>0.1*卖一价)] / ETH :最少买入0.01个ETH 的金额(金额>0.01*卖一价)] 市价卖单不传price
//            amount
//    Double
//            否
//    交易数量 [限价卖单（必填）：BTC 数量大于等于0.01 / LTC 数量大于等于0.1 / ETH 数量大于等于0.01 | 市价卖单（必填）： BTC :最少卖出数量大于等于0.01 / LTC :最少卖出数量大于等于0.1 / ETH :最少卖出数量大于等于0.01] 市价买单不传amount
//            sign
//    String
//            是
//    请求参数的签名
}
