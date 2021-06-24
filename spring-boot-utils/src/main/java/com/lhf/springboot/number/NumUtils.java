package com.lhf.springboot.number;

/**
 * @ClassName NumUtils
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/24 11:40
 **/
public class NumUtils {

    /**
     * 将Object数据类型转化为Integer
     * @param objData
     * @return
     */
    public static Integer checkDataType(Object objData){
        Integer value = 0;
        if("class java.lang.String".equals(getType(objData))){
            //System.out.println("string.....");
            value = Integer.valueOf(Double.valueOf(objData.toString()).intValue());
        }else if("class java.lang.Double".equals(getType(objData))){
            //System.out.println("Double.....");
            value = Double.valueOf(objData.toString()).intValue();
        }else if ("class java.lang.Float".equals(getType(objData))){
            //System.out.println("Float.....");
            value = Float.valueOf(objData.toString()).intValue();
        }else {
            //System.out.println("other.....");
            value = Double.valueOf(objData.toString()).intValue();
        }
        return value;
    }

    /**
     * 获取数据类型
     * @param a
     * @return
     */
    public static String getType(Object a) {
        return a.getClass().toString();
    }
}
