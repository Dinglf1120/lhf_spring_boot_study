package com.lhf.springboot.map;

/**
 * @ClassName StaticUtils
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 11:21
 **/
public class StaticUtils {

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        if(obj==null){
            return true;
        }
        if("".equals(obj)){
            return true;
        }
        return false;
    }
}
