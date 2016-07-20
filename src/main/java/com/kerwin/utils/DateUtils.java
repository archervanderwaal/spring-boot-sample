package com.kerwin.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/21.
 * 时间工具
 */
public class DateUtils {

    /**
     * 两个时间相差秒数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差秒数
     */
    public static int secondOfDate(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long v = to - from;
        return (int) (v / 1000L);
    }

    /**
     * 两个时间相差分钟数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差分钟数
     */
    public static int minuteOfDate(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long v = to - from;
        return (int) (v / 1000L / 60L);
    }

    /**
     * 两个时间相差小时数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差小时数
     */
    public static int hourOfDate(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long v = to - from;
        return (int) (v / 1000L / 60L / 60L);
    }

    /**
     * 两个时间相差天数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差天数
     */
    public static int yearOfDate(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(fromDate);
        to.setTime(toDate);
        return to.get(1) - from.get(1);
    }


    /**
     * 两个时间相差月数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差月数
     */
    public static int monthOfDate(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(fromDate);
        to.setTime(toDate);
        int year = to.get(1) - from.get(1);
        return (year * 12 + to.get(2)) - from.get(2);
    }

    /**
     * 两个时间相差年数
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 相差年数
     */
    public static int dayOfDate(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long v = to - from;
        return (int) (v / 1000L / 3600L / 24L);
    }

    /**
     * 在一个时间基础上增加秒数
     *
     * @param date   原时间
     * @param second 秒
     * @return 增加后的时间
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(13, calendar.get(13) + second);
        return calendar.getTime();
    }

    /**
     * 在一个时间基础上增加分钟
     *
     * @param date   原时间
     * @param minute 分钟
     * @return 增加后的时间
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(12, calendar.get(12) + minute);
        return calendar.getTime();
    }

    /**
     * 在一个时间基础上增加小时
     *
     * @param date 原时间
     * @param hour 小时
     * @return 增加后的时间
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, calendar.get(11) + hour);
        return calendar.getTime();
    }

    /**
     * 在一个时间基础上增加天数
     *
     * @param date 原时间
     * @param day  天数
     * @return 增加后的时间
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.get(6) + day);
        return calendar.getTime();
    }

    /**
     * 在一个时间基础上增加月数
     *
     * @param date  原时间
     * @param month 月
     * @return 增加后的时间
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, calendar.get(2) + month);
        return calendar.getTime();
    }

    /**
     * 在一个时间基础上增加年数
     *
     * @param date 原时间
     * @param year 年
     * @return 增加后的时间
     */
    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1, calendar.get(2) + year);
        return calendar.getTime();
    }

    /**
     * 根据给定的数据创建一个时间
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 时间
     */
    public static Date newDate(int year, int month, int day) {
        return newDate(year, month, day, 0, 0, 0);
    }

    /**
     * 根据给定的数据创建一个时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return 时间
     */
    public static Date newDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.set(11, hour);
        c.set(12, minute);
        c.set(13, second);
        c.set(14, 999);
        return c.getTime();
    }

    /**
     * 将String类型的日期转成Long
     *
     * @param time 是一个带分割符的日期
     * @return .
     */
    public static Long replaceAllDateChar(String time) {
        return new Long(time.replaceAll("[^\\d]+", ""));
    }

    /**
     * @param dateStr 日期字符串
     * @return 将String日期转换成 java.util.Date
     * @throws ParseException
     */
    public static Date StringToDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        return dateFormat.parse(dateStr);
    }

    /**
     * 截取日期数据的时、分、秒
     *
     * @param date 时间
     * @return 分割后的日期
     * @throws ParseException
     */
    public static String DateSplit(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH时mm分");
        return format.format(date);
    }

    /**
     * 通过一个java.util.Date类型计算星期几
     * 日期为null， 返回空串
     *
     * @param date 日期
     * @return 周日——周一
     */
    public static String getDayOfWeek(Date date) {
        if (null == date) return "";
        DateTime time = new DateTime(date);
        String week;
        switch (time.getDayOfWeek()) {
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
            default:
                week = "日";
        }
        return week;
    }
}
