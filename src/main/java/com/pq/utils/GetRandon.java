package com.pq.utils;

import java.util.Random;

public class GetRandon {

    public static String getRandom(Integer length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
          String charOrNum = random.nextInt(2)%2 == 0 ? "char":"num";//用这个来产生的是数字还是字母
            if("char".equalsIgnoreCase(charOrNum)){
                 //字母的大小写问题
                 int choice = random.nextInt(2)%2 == 0?65:97;//加上65 97 就是判断大小写问题
                val +=(char)(choice + random.nextInt(26));
            }
            if("num".equalsIgnoreCase(charOrNum)){
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
