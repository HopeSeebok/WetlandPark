package com.yunwei.wetlandpark.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.utils
 * @Description:日期时间工具类
 * @date 2016/8/11 8:45
 */
public class IDateTimeUtils {
    /**
     * 时间计数器，最多只能到99小时，如需要更大小时数需要改改方法
     *
     * @param time
     * @return
     */
    public static String showTimeCount(long time) {
        if (time >= 360000000) {
            return "00:00:00";
        }
        String timeCount = "";
        long hourc = time / 3600000;
        String hour = "0" + hourc;
        hour = hour.substring(hour.length() - 2, hour.length());

        long minuec = (time - hourc * 3600000) / (60000);
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());

        long secc = (time - hourc * 3600000 - minuec * 60000) / 1000;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());
        timeCount = hour + ":" + minue + ":" + sec;
        return timeCount;
    }

    /**
     * 时间戳格式化
     *
     * @param time
     * @param str
     * @return
     */
    public static String formatDate(long time, String str) {
        return new SimpleDateFormat(str).format(time);
    }


    public static String getDateNow() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    public static String getDateNow(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    public static String changesformDate(String format, String time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String endTime = sDateFormat.format(new Date(time));
        return endTime;
    }

    public static int getTimeMinuteNow() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return hour*60+minute;
    }


    public static String formatDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
    }

    public static String formatDateHours(long time) {
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(time));
    }

    /**
     * 判断日期是否在当前天 (默认格式：yyyy-MM-dd HH:mm:ss)
     * du yang
     *
     * @return
     */
    public static boolean varifyToday(String time, String format) {
        boolean isInSameDay;
        SimpleDateFormat simpleDateFormat = null;
        if (format != null) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            Date date = simpleDateFormat.parse(time);
            Date dateTodayStart = simpleDateFormat.parse(new SimpleDateFormat("yyyy-MM-dd ").format(new java.util.Date()) + "00:00:00");
            Date dateTodayEnd = simpleDateFormat.parse(new SimpleDateFormat("yyyy-MM-dd ").format(new java.util.Date()) + "23:59:59");
            isInSameDay = date.before(dateTodayEnd) && date.after(dateTodayStart);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return isInSameDay;
    }

    /**
     * 时间比较
     * author:zls
     *
     * @param date1 时间1
     * @param date2 时间2
     */
    public static boolean TimeCompareIsDate1Big(String date1, String date2) {
        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date beginTime = CurrentTime.parse(date1);
            Date endTime = CurrentTime.parse(date2);
            //判断大小
            long time = endTime.getTime();
            long time1 = beginTime.getTime();
            if ((time  - time1) >= 0) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     * author:zls
     *
     * @param time
     * @return
     */
    public static String getTimes(String time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type);//例"yyyy年MM月dd日HH时mm分ss秒"
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i));
//        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    public static String getTimes(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//例"yyyy年MM月dd日HH时mm分ss秒"
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i));
//        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    public static String getTimes(long time) {
        long second = time / 1000;
        long minute = second / 60;
        long hour = minute / 60;
        String h = hour < 10 ? ("0" + hour) : hour + "";
        String m = (minute - (hour * 60)) < 10 ? "0" + (minute - (hour * 60)) : (minute - (hour * 60)) + "";
        String s = (second - (minute * 60)) < 10 ? "0" + (second - (minute * 60)) : (second - (minute * 60)) + "";
        return h + "时" + m + "分" + s + "秒";
    }


    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     * author:zls
     *
     * @param time
     * @return
     */
    public static String getTimestamp(String time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type, Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
//            times = stf.substring(0, 10);
            times = stf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 输入时间戳变星期
     * author：zls
     * @param time
     * @return
     */
    public static String changeweekOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }

    /**
     * 格式化时间转换为UNIX时间戳(单位为秒)
     * du yang
     * @param time
     * @param format
     * @return
     */
    public static long getTimeStamp(String time,String format) {
        if (TextUtils.isEmpty(format)) {
            format="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format,
                Locale.CHINA);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime()/1000;
    }

}
