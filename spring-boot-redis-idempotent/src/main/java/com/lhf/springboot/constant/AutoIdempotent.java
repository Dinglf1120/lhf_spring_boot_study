package com.lhf.springboot.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: AutoIdempotent
 * @Author: liuhefei
 * @Description: 自定义注解
 * 目的是把它添加在需要实现幂等的方法上，凡是某个方法注解了它，都会实现自动幂等。
 * 后台利用反射如果扫描到这个注解，就好处理这个方法实现自动幂等，
 * 使用元注解ElementType.METHOD表示它只能放在方法上，etentionPolicy.RUNTIME表示它在运行时。
 * @Date: 2020/4/15 14:37
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {
}
