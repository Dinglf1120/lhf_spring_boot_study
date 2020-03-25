package com.lhf.springboot.common;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: EnumUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/12/4 13:23
 */
public class EnumUtil {

    private static final Logger logger = LoggerFactory.getLogger(EnumUtil.class);


    /**
     * 将枚举转换为key-value形式
     * 例如：EnumUtil.getEnumJsonString(SysEnum.class); 将会转换为[{"key":"","name": "" ,"time": _ }...]
     * @param lass 待转换枚举类型
     * @return 枚举Map形式的列表
     */
    public static List<Map<String, Object>> getEnumJsonString(Class lass) {
        try {
            List<Map<String, Object>> result = EnumUtil.enumValueToMap(lass);
            return result;
        } catch (SecurityException | IllegalArgumentException e) {
            throw new RuntimeException("枚举类转换字符串时异常，类名:" + lass.getName() + "异常：" + e.getMessage());
        }
    }

    /**
     * 枚举项转换为Map形式
     * @param em 枚举项
     * @return 该枚举项的Map形式
     */
    public static Map<String, Object> enumValueToJson(Enum<?> em) {

        Map<String, Object> maps = new HashMap<>();
        HashSet<String> ignoreMethod = Sets.newHashSet("values", "valueOf", "equals", "toString",
                "hashCode", "compareTo", "compareTo", "valueOf", "getDeclaringClass", "wait",
                "getClass", "notify", "notifyAll");
        Class<?> c = em.getClass();
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            if (ignoreMethod.contains(m.getName()) || m.getParameterCount() > 0) {
                continue;
            }
            String key = m.getName();
            Object val = "";
            try {
                val = m.invoke(em);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e1) {
                val = e1.getMessage();
            }
            maps.put(key, val);
        }
        return maps;
    }

    public static List<Map<String, Object>> enumValueToMap(Class<?> lass) {

        List<Map<String, Object>> result = new ArrayList<>();
        if (!Enum.class.isAssignableFrom(lass)) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", lass.getSimpleName() + " is not a Enum Type.");
            result.add(map);
            return result;
        }
        HashSet<String> ignoreMethod = Sets.newHashSet("values", "valueOf", "equals", "toString",
                "hashCode", "compareTo", "compareTo", "valueOf", "getDeclaringClass", "wait",
                "getClass", "notify", "notifyAll");
        Set<Method> methods = Stream.of(lass.getMethods())
                .filter(e -> !ignoreMethod.contains(e.getName())
                        && e.getParameterTypes().length == 0
                        && !e.getReturnType().getName().equals("void"))
                .collect(Collectors.toSet());
        Enum<?>[] enums = new Enum<?>[0];
        try {
            enums = (Enum[]) lass.getMethod("values").invoke(null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            logger.error(e.getMessage(), e);
        }
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (Enum e : enums) {
            Map<String, Object> map = enumValueToJson(e);
            result.add(map);
        }
        return result;
    }
}
