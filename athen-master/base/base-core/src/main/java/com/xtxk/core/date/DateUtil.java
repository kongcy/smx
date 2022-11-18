package com.xtxk.core.date;



import com.xtxk.core.util.U;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /** 当前时间 */
    public static Date now() {
        return new Date();
    }

    /** 返回 yyyy-MM-dd HH:mm:ss 格式的当前时间 */
    public static String nowTime() {
        return now(DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }
    /** 返回今天的 yyyy-MM-dd 格式 */
    public static String yeahMonthDay() {
        return now(DateFormatType.YYYY_MM_DD);
    }
    /** 返回今天的 yyyyMMdd 格式 */
    public static String today() {
        return now(DateFormatType.YYYYMMDD);
    }

    /** 获取当前时间日期的字符串 */
    public static String now(DateFormatType dateFormatType) {
        return format(now(), dateFormatType);
    }
    /** 格式化日期 yyyy-MM-dd */
    public static String formatDate(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD);
    }
    /** 格式化时间 HH:mm:ss */
    public static String formatTime(Date date) {
        return format(date, DateFormatType.HH_MM_SS);
    }
    /** 格式化日期和时间 yyyy-MM-dd HH:mm:ss */
    public static String formatFull(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    private static String format(Date date, String type) {
        return new SimpleDateFormat(type).format(date);
    }

    public static String format(Date date, DateFormatType dateType) {
        if (date == null) return U.EMPTY;
        if (dateType == null) return U.EMPTY;

        return format(date, dateType.getValue());
    }

    public static Date parseSpecified(String source) {
        if (U.isBlank(source)) return null;

        for (DateFormatType formatType : DateFormatType.values()) {
            Date date = parse(source, formatType.getValue());
            if (date != null) return date;
        }
        return null;
    }

    private static Date parse(String date, String type) {
        try {
            return new SimpleDateFormat(type).parse(date.trim());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parse(String date,DateFormatType type) {
        if (U.isBlank(date)) return null;
        try {
            return new SimpleDateFormat(type.getValue()).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 获取一个日期所在天的最开始的时间(00:00:00 000), 对日期段查询尤其有用 */
    public static Date getDayStart(Date date) {
        return getDay(date, "yyyy-MM-dd 00:00:00 000");
    }
    private static Date getDay(Date date, String type) {
        if (date == null) return null;

        return parse(format(date, type), DateFormatType.YYYY_MM_DD_HH_MM_SS_SSS.getValue());
    }
    /** 获取一个日期所在天的最晚的时间(23:59:59 999), 对日期查询尤其有用 */
    public static Date getDayEnd(Date date) {
        if (date == null) return null;

        return getDay(date, "yyyy-MM-dd 23:59:59 999");
    }

    private static Date add(Date date, int field, int num) {
        if(date==null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, num);
        return c.getTime();
    }
    /**
     * 取得指定日期 N 天后的日期
     *
     * @param day 正数表示多少天后, 负数表示多少天前
     */
    public static Date addDays(Date date, int day) {
        return add(date, Calendar.DAY_OF_MONTH, day);
    }
    /**
     * 取得指定日期 N 个月后的日期
     *
     * @param month 正数表示多少月后, 负数表示多少月前
     */
    public static Date addMonths(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }
    /**
     * 取得指定日期 N 天后的日期
     *
     * @param year 正数表示多少年后, 负数表示多少年前
     */
    public static Date addYears(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }
    /**
     * 取得指定日期 N 分钟后的日期
     *
     * @param minute 正数表示多少分钟后, 负数表示多少分钟前
     */
    public static Date addMinute(Date date, int minute) {
        return add(date, Calendar.MINUTE, minute);
    }
    /**
     * 取得指定日期 N 小时后的日期
     *
     * @param hour 正数表示多少小时后, 负数表示多少小时前
     */
    public static Date addHours(Date date, int hour) {
        return add(date, Calendar.HOUR_OF_DAY, hour);
    }
    /**
     * 取得指定日期 N 秒后的日期
     *
     * @param second 正数表示多少秒后, 负数表示多少秒前
     */
    public static Date addSeconds(Date date, int second) {
        return add(date, Calendar.SECOND, second);
    }
    /**
     * 取得指定日期 N 周后的日期
     *
     * @param week 正数表示多少周后, 负数表示多少周前
     */
    public static Date addWeeks(Date date, int week) {
        return add(date, Calendar.WEEK_OF_MONTH, week);
    }

    /** 传入的时间晚于当前时间就返回传入的时间, 否则就返回当前时间 */
    public static Date before(Date date) {
        Date now = now();
        return now.after(date) ? now : date;
    }

    /** 传入的时间是不是当月当日. 用来验证生日 */
    public static boolean wasBirthday(Date date) {
        Calendar now = Calendar.getInstance();

        Calendar birthday = Calendar.getInstance();
        birthday.setTime(date);
        return now.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)
                && now.get(Calendar.DAY_OF_MONTH) == birthday.get(Calendar.DAY_OF_MONTH);
    }

    /** 返回指定日期的当月第一毫秒 */
    public static Date getFirstDayOfMonth(Date date) {
        return getDay(date, "yyyy-MM-01 00:00:00 000");
    }

    /** 返回指定日期的当月最后一毫秒 */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /** 返回上个星期一的第一毫秒 */
    public static Date getLastMonday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.roll(Calendar.WEEK_OF_YEAR, -1);
        return calendar.getTime();
    }

    /** 返回上个星期天的最后一毫秒 */
    public static Date getLastDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /** 返回指定日期的那个星期一的第一毫秒(星期一为一周的第一天) */
    public static Date getMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /** 返回指定日期的那个星期天的最后一毫秒(星期天为一周的最后一天) */
    public static Date getSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.WEEK_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate)
    {
        smdate = parse(formatDate(smdate),"yyyy-MM-dd");
        bdate = parse(formatDate(bdate), "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(smdate, "yyyy-MM-dd"));
        long time1 = cal.getTimeInMillis();
        cal.setTime(parse(bdate, "yyyy-MM-dd"));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 日期去除时间，只保留日期
     * @param d
     * @return
     */
    public static Date onlyDate(Date d){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }
}
