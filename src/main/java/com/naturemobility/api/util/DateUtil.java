package com.naturemobility.api.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil {

    public static String getYear(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String getMonth(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String getDay(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String getHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String getMinute(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public static String getSecond(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("ss");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }
}
