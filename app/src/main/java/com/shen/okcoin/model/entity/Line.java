package com.shen.okcoin.model.entity;


/**
 * Created by shenwangqiang on 2017/8/31.
 */
public class Line {

    private String time;
    private String open;
    private String high;
    private String low;
    private String close;
    private String count;

    public Line() {

    }

    public Line(String[] array) {
        this.time = array[0];
        this.open = array[1];
        this.high = array[2];
        this.low = array[3];
        this.close = array[4];
        this.count = array[5];
    }

    public float getTime() {
        return Long.parseLong(time);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public float getClose() {
        return Float.parseFloat(close);
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
