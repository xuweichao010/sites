package com.xwc.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * 创建人：徐卫超
 * 时间: 2018/5/12
 * 功能：时间工具类
 * 描述：对SimpleDateFormat 和 Calendar对象的增强
 */
@SuppressWarnings("all")
public class DateFormatUtils {

    public static final String GMT8_TIME = "HH:mm:ss";
    public static final String GMT8_DATE = "yyyy-MM-dd";
    public static final String GMT8_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String CHINA = "Asia/Shanghai";

    /**
     * Date对象按照指定格式转成时间字符串
     *
     * @param date      时间
     * @param formatTxt 格式
     * @return 时间字符串
     */
    public static String formatDateObj(final Date date, final String formatTxt, String zoneId) {
        SimpleDateFormat format = new SimpleDateFormat(formatTxt);
        if (zoneId != null) format.setTimeZone(TimeZone.getTimeZone(zoneId));
        return format.format(date);
    }

    /**
     * Date对象转成标准日期时间字符串
     *
     * @param date 时间对象
     * @return 时间字符串 格式是：yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return formatDateObj(date, GMT8_DATE_TIME, null);
    }

    /**
     * Date对象转成标准日期字符串
     *
     * @param date 时间对象
     * @return 日期字符串格式
     */
    public static String formatDate(final Date date) {
        return formatDateObj(date, GMT8_DATE, null);
    }

    /**
     * 用Date对象转成标准时间字符串
     *
     * @param date 时间对象
     * @return 日期格式：yyyy-MM-dd
     */
    public static String formatTime(final Date date) {
        return formatDateObj(date, GMT8_TIME, null);
    }


    /**
     * 时间字符串按照指定格式转成Date对象
     *
     * @param dateStr
     * @param formatTxt
     * @return
     */
    public static Date parseDateStr(final String dateStr, final String formatTxt, String zoneId) {
        SimpleDateFormat format = new SimpleDateFormat(formatTxt);
        if (zoneId != null) format.setTimeZone(TimeZone.getTimeZone(zoneId));
        Date parse = null;
        try {
            parse = format.parse(dateStr.trim());
        } catch (ParseException e) {
            throw new RuntimeException("无法转换时间格式！" + dateStr + "转换成" + formatTxt + "格式");
        }
        return parse;
    }

    /**
     * 标准日期时间字符串转换成Date对象
     *
     * @param dateStr 时间字符串 格式是：（yyyy-MM-dd HH:mm:ss）
     * @return 时间对象
     */
    public static Date parseDateTime(final String dateStr) {
        return parseDateStr(dateStr, GMT8_DATE_TIME, null);
    }

    /**
     * 标准日期字符串转成Date对象
     *
     * @param dateStr 时间字符串 格式必须满足：yyyy-MM-dd
     * @return
     */
    public static Date parseDate(final String dateStr) {
        return parseDateStr(dateStr, GMT8_DATE, null);
    }


    /**
     * 获取距离1970-1-1 00:00:00经过了多少秒
     *
     * @return
     */
    public static long getSecond() {
        return new Date().getTime() / 1000;
    }

    /**
     * 获取距离1970-1-1 00:00:00经过了多少毫秒
     *
     * @return
     */
    public static long getMillisecond() {
        return new Date().getTime();
    }
}
