package com.lhf.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: AccessLimit
 * @Author: liuhefei
 * @Description: 自定义注解
 * 在需要保证  接口防刷限流  的Controller的方法上使用此注解
 * @Date: 2020/4/15 18:09
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    int maxCount();  //最大访问次数

    int seconds();  //固定时间：单位：s
}
