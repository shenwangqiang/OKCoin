package com.shen.okcoin.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shenwangqiang on 2017/8/30.
 */
public class CommonUtil {
    /**
     * judge the list is null or empty
     */
    public static boolean isEmpty(List<? extends Object> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * judge the set is null or empty
     */
    public static boolean isEmpty(Set<? extends Object> set) {
        if (set == null || set.isEmpty())
            return true;
        return false;
    }

    /**
     * judge the map is null or empty
     */
    public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
        if (map == null || map.isEmpty())
            return true;
        return false;
    }

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 加载图片
     *
     * @param context
     * @param url        地址
     * @param imageView  显示view
     * @param errorResId 默认图片
     */
    public static void loadImage(Context context, String url, final ImageView imageView, final int errorResId) {
        if (isEmpty(url)) {
            // 空地址直接加载默认图片
            Glide.with(context).load(errorResId).into(imageView);
        } else if (new File(url).exists()) {
            File file = new File(url);
//            RequestOptions options = new RequestOptions().signature(new StringSignature(file.lastModified() + ""));
            // 如果是本地图片
            Glide.with(context).load(file).into(imageView);
        } else {
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
            RequestOptions options = new RequestOptions().placeholder(errorResId).dontAnimate();
            // 如果是本地图片
            // 加载网络图片
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * http错误处理
     * @param throwable
     */
    public static void httpError(Throwable throwable) {

    }

    /**
     * 将字符串三位一个逗号显示
     * @param str
     * @return
     */
    public static String getNumStr(String str){
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(Double.parseDouble(str));
    }
}
