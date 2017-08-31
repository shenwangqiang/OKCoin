package com.shen.okcoin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class Constants {
    // banner图片地址
    public static final String BANNER_IMG_1 = "http://mpic.tiankong.com/8c5/64a/8c564a5c858c1ac7a1cfb7caec065b8c/640.jpg";
    public static final String BANNER_IMG_2 = "http://mpic.tiankong.com/373/a46/373a463dc396667e6c11f499a0ff30cd/640.jpg";
    public static final String BANNER_IMG_3 = "http://mpic.tiankong.com/54b/e75/54be757683152b5b05559a7063619503/640.jpg";
    public static final String BANNER_IMG_4 = "http://mpic.tiankong.com/8d2/c9d/8d2c9d0888c9638f6c091ac7545a48ce/att1c0.jpg";

    // http
    public static final String URL_BASE = "https://www.okcoin.cn/";

    public static final ArrayList<String> CNY_LIST = new ArrayList<String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add("btc_cny");
            add("ltc_cny");
            add("eth_cny");
            add("etc_cny");
            add("bcc_cny");
        }
    };
    /**
     *
     */
    public static final HashMap<String, String> CNY_MAP = new HashMap<String, String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put("btc_cny", "比特币");
            put("ltc_cny", "莱特币");
            put("eth_cny", "以太坊");
            put("etc_cny", "以太经典");
            put("bcc_cny", "比特现金");
        }
    };


}
