package com.lnkj.privateshop.utils;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class DateFormatUtils {
    /**
     * @param
     *
     * @return 时:分:秒
     *
     */
    public static String getHours(long time) {
        long second = time / 1000;
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;
        String rHour = "";
        String rMin = "";
        String rSs = "";
        // 时
        if (hour < 10) {
            rHour = "0" + hour;
        } else {
            rHour = hour + "";
        }
        // 分
        if (minute < 10) {
            rMin = "0" + minute;
        } else {
            rMin = minute + "";
        }
        // 秒
        if (sec < 10) {
            rSs = "0" + sec;
        } else {
            rSs = sec + "";
        }
         return hour + "小时" + minute + "分钟" + sec + "秒";

    }

    /**
     * 小时
     * @param time
     * @return
     */
    public static String getHour(long time) {
        long second = time / 1000;
        long hour = second / 60 / 60;
        String rHour = "";
        // 时
        if (hour < 10) {
            rHour = "0" + hour;
        } else {
            rHour = hour + "";
        }

        // return hour + "小时" + minute + "分钟" + sec + "秒";
        return rHour ;

    }

    /**
     * 分
     * @param time
     * @return
     */
    public static String getMin(long time) {
        long second = time / 1000;
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        String rMin = "";
        // 分
        if (minute < 10) {
            rMin = "0" + minute;
        } else {
            rMin = minute + "";
        }
        // return hour + "小时" + minute + "分钟" + sec + "秒";
        return  rMin ;

    }
    /**
     * @param
     *
     * @return 秒
     *
     */
    public static String getSs(long time) {
        long second = time / 1000;
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;
        String rSs = "";
        // 秒
        if (sec < 10) {
            rSs = "0" + sec;
        } else {
            rSs = sec + "";
        }
        // return hour + "小时" + minute + "分钟" + sec + "秒";
        return rSs;

    }
}
