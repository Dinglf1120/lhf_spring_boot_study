package com.lhf.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ApiIdempotent
 * @Author: liuhefei
 * @Description: 自定义注解
 * 在需要保证 接口幂等性 的Controller的方法上使用此注解
 * @Date: 2020/4/15 18:13
 */
@Target({ElementType.METHOD})      //作用于方法上
@Retention(RetentionPolicy.RUNTIME)  //运行时
public @interface ApiIdempotent {
}
