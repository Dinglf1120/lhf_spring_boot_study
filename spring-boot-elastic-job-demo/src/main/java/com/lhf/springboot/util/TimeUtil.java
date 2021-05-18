package com.lhf.springboot.util;

import java.text.SimpleDateFormat;

/**
 * @ClassName: TimeUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/14 17:46
 */
public class TimeUtil {

    public static String mill2Time(long mill){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(mill);
    }
}
