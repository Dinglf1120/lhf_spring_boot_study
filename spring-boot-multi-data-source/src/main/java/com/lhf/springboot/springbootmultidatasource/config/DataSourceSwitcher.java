package com.lhf.springboot.springbootmultidatasource.config;

import java.lang.annotation.*;

/**
 * @ClassName DataSourceSwitcher
 * @Desc 切换注解和 Aop 配置
 * 首先我们来定义一个@DataSourceSwitcher 注解,拥有两个属性 ① 当前的数据源 ② 是否清除当前的数据源,
 * 并且只能放在方法上,(不可以放在类上,也没必要放在类上,因为我们在进行数据源切换的时候肯定是方法操作),该注解的主要作用就是进行数据源的切换,
 * 在 dao 层进行操作数据库的时候,可以在方法上注明表示的是当前使用哪个数据源;
 * @Author liuhefei
 * @Date 2021/6/24 18:21
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {
    /**
     * 默认数据源
     * @return
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;
    /**
     * 清除
     * @return
     */
    boolean clear() default true;

}