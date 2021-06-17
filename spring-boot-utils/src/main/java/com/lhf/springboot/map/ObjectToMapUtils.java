package com.lhf.springboot.map;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ObjectToMapUtils
 * @Desc Object对象转化为Map
 * @Author liuhefei
 * @Date 2021/6/4 11:19
 **/
public class ObjectToMapUtils {

    public Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
            if(StaticUtils.isNull(value)){
                map.put(fieldName, "0");
            }
        }
        return map;
    }
}
