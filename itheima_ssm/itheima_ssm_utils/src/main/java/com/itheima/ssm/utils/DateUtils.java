package com.itheima.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Converter<String, Date> {

    //日期(数据库, 实体类)转换为字符串(页面传递)
    public static String date2String(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(date);
        return format;
    }

    //将日期字符串转换成日期对象 返回
    @Override
    public Date convert(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
