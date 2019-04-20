package com.pq.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 彭庆创建与2019/4/9
 */
public class DateUtils {

   public  static String getFillDate(Date date){
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy年-MM月-dd日");
        return  sbf.format(date);
    }

   public  static  String getDate(Date date){
        SimpleDateFormat sbf = new SimpleDateFormat("MM月dd日");
        return  sbf.format(date);
    }
    public static String getFillDateAndTime(Date date){
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
        return  sbf.format(date);
    }
    public static  String getTimes(Date date){
        SimpleDateFormat sbf = new SimpleDateFormat("HH:mm");
        return  sbf.format(date);
    }

    public static Long getHours(String startDate, String endDate){
        try {
            SimpleDateFormat sbf = new SimpleDateFormat("HH:mm");
            Date parse1 = sbf.parse(startDate);
            Date parse2 = sbf.parse(endDate);
            Long diff =parse2.getTime() - parse1.getTime();
            Long hours = diff/(1000 * 60 * 60);
            return hours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return null;
    }
    public static Integer compareDate(String lDate,String sDate){
        try {
            SimpleDateFormat sbf = new SimpleDateFormat("HH:mm");
            Date parse1 = sbf.parse(lDate);
            Date parse2 = sbf.parse(sDate);
            if(parse1.getTime() > parse2.getTime()){
                return  0;
            }else{
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
   public static String getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch(i){
            case 1:{
                week = "星期日";
                break;
            }
            case 2:{
                week = "星期一";
                break;
            }
            case 3:{
                week = "星期二";
                break;
            }
            case 4:{
                week = "星期三";
                break;
            }
            case 5:{
                week = "星期四";
                break;
            }
            case 6:{
                week = "星期五";
                break;
            }
            case 7:{
                week = "星期六";
                break;
            }
        }
        return week;
    }

}
