package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 获取标准化时间字符串的工具类
 */
public  final class DataTimeUtil {

    //获取当下具体时间的标准化字符串
    public static  String getNowDateTimeString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(System.currentTimeMillis());
    }


    //把标准化具体时间字符串转成时间戳
    public static long parseDateTimeStamp(String s) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t = 0;
        try {
            t=df.parse(s).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    //具体时间比较，比当前时间晚返回ture,否则返回flase
    public static  boolean dateTimeIsAfterNow(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t = 0;
        try {
            t=df.parse(time).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return t>System.currentTimeMillis();
    }




    //获取当下日期的标准化字符串
    public static String getNowDateString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }


    //把日期的标准化字符串转化为date类型
    public static  Date parseDateStamp(String s){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    //日期比较，比当前日期晚返回ture,否则返回flase
    public static boolean dateIsAfterNow(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date=df.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date.after(new Date());
    }






}
