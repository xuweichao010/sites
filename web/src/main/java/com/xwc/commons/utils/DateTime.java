package com.xwc.commons.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/31  16:45
 * 功能：
 * 业务：
 */
@SuppressWarnings("all")
public class DateTime {
    private Calendar calendar;

    public DateTime() {
        this.calendar = Calendar.getInstance();
    }

    public DateTime(Date date) {
        this.calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    public DateTime(long millis) {
        this.calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
    }

    /**
     * 在Date对象上添加年的偏移量
     *
     * @param year 年偏移量
     * @return DateTime
     */
    public DateTime addYear(int year) {
        calendar.add(Calendar.YEAR, year);
        return this;
    }

    /**
     * 在Date对象上设置年份
     *
     * @param year 年
     * @return DateTime
     */
    public DateTime setYear(int year) {
        calendar.set(Calendar.YEAR, year);
        return this;
    }

    /**
     * 在Date对象上添加月的偏移量
     *
     * @param month 月偏移量
     * @return
     */
    public DateTime addMonth(int month) {
        calendar.add(Calendar.MONTH, month);
        return this;
    }

    /**
     * 在Date对象上设置月份值
     *
     * @param month 月份
     * @return 时间对象
     */
    public DateTime setMonth(int month) {
        calendar.set(Calendar.MONTH, month - 1);
        return this;
    }

    /**
     * 在Date对象上添加天的偏移量
     *
     * @param day 天偏移量
     * @return 时间对象
     */
    public DateTime addDay(int day) {
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    /**
     * 在Date对象上设置日期值
     *
     * @param day 天
     * @return
     */
    public DateTime setDay(int day) {
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    /**
     * 在Date对象上添加小时的偏移量
     *
     * @param hour 小时偏移量
     * @return
     */
    public DateTime addHour(int hour) {
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     * 在Date对象上设置小时值
     *
     * @param hour 小时
     * @return 时间对象
     */
    public DateTime setHour(int hour) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     * 在Date对象上添加分钟的偏移量
     *
     * @param minute 分钟偏移量
     * @return 时间对象
     */
    public DateTime addMinute(int minute) {
        calendar.add(Calendar.MINUTE, minute);
        return this;
    }

    /**
     * 在Date对象上设置分钟的值
     *
     * @param minute 分钟
     * @return 时间对象
     */
    public DateTime setMinute(int minute) {
        calendar.set(Calendar.MINUTE, minute);
        return this;
    }

    /**
     * 在Date对象上添加秒的偏移量
     *
     * @param second 秒偏移量
     * @return
     */
    public DateTime addSecond(int second) {
        calendar.add(Calendar.SECOND, second);
        return this;
    }

    /**
     * 在Date对象上设置秒的值
     *
     * @param second 秒
     * @return
     */
    public DateTime setSecond(int second) {
        calendar.set(Calendar.SECOND, second);
        return this;
    }

    public DateTime set(int field, int offset) {
        calendar.set(field, offset);
        return this;
    }

    public Date getTime() {
        return calendar.getTime();
    }
}