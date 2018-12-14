package com.lnkj.privateshop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public  class DateUtils {
//    //时间选择器
//    public static void  dataSelector(final TextView meTextView,Context context) {
//
//
//
//        TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                meTextView.setText(AppDateMgr.dateToString(date, "yyyy-MM-dd"));
//            }
//        }).setType(new boolean[]{true, true, true, false, false, false})
//                .setContentSize(14)//滚轮文字大小
//                .setSubCalSize(14)
//                .setSubmitColor(Color.parseColor("#0061b0"))//确定按钮文字颜色
//                .setCancelColor(Color.parseColor("#0061b0"))//取消按钮文字颜色
//                .build();
//        pvTime.setDate(Calendar.getInstance());
//        pvTime.show();
//    }
    //获取当前系统日期
    public static String getCurrentDate(){
        String date = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date = formatter.format(curDate);
        return date;
    }

    /**
     *
     * @param formatString 格式化的样式
     * @param datestr 时间
     * @return  时间戳String
     */

    //字符串时间转换为时间戳
    public static long conversionDate(String formatString,String datestr){
         Date date =null;
        try {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(formatString);
          date = simpleDateFormat .parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeStemp = date.getTime()/1000;
        return  timeStemp;
    }
    /**
     * 返回的字符串形式是形如：2013-10-20 20:58
     * */
    public static String formatTimeInMillis(long timeInMillis ,String format) {
        Date date = new Date(timeInMillis*1000);
        SimpleDateFormat sd = new SimpleDateFormat(format);
        return sd.format(date);
    }
    /**
     * 格式化时间（输出类似于 刚刚, 4分钟前, 一小时前, 昨天这样的时间）
     *
     * @param time    需要格式化的时间 如"2014-07-14 19:01:45"
     * @param pattern 输入参数time的时间格式 如:"yyyy-MM-dd HH:mm:ss"
     *                <p/>如果为空则默认使用"yyyy-MM-dd HH:mm:ss"格式
     * @return time为null，或者时间格式不匹配，输出空字符""
     */
    public static String formatDisplayTime(String time, String pattern) {
        String display = "";
        int tMin = 60 * 1000;
        int tHour = 60 * tMin;
        int tDay = 24 * tHour;

        if (time != null) {
            try {
                Date tDate = new SimpleDateFormat(pattern).parse(time);
                Date today = new Date();
                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - tDay);
                if (tDate != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - tDate.getTime();
                    if (tDate.before(thisYear)) {
                        display = new SimpleDateFormat("yyyy年MM月dd日").format(tDate);
                    } else {

                        if (dTime < tMin) {
                            display = "刚刚";
                        } else if (dTime < tHour) {
                            display = (int) Math.ceil(dTime / tMin) + "分钟前";
                        } else if (dTime < tDay && tDate.after(yesterday)) {
                            display = (int) Math.ceil(dTime / tHour) + "小时前";
                        } else if (tDate.after(beforeYes) && tDate.before(yesterday)) {
                            display = "昨天" + new SimpleDateFormat("HH:mm").format(tDate);
                        } else {
                            display = halfDf.format(tDate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return display;
    }
}
