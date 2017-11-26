package com.xsg.ttshop.common.utils;

import java.util.Random;

/**
 * User: 57140
 * Date: 2017/11/23
 * Time: 17:19
 * Version:V1.0
 */
public class IDUtils {
    public static long getItemId(){
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new  Long(str);
        return id;
    }
}
