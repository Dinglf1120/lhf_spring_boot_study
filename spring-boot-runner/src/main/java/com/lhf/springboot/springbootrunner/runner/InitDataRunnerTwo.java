package com.lhf.springboot.springbootrunner.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: InitDataRunnerTwo
 * @Author: liuhefei
 * @Description: 实现CommandLineRunner接口实现项目启动时初始化数据
 * @Date: 2020/4/10 12:04
 */
@Slf4j
@Component
@Order(value = 3)
public class InitDataRunnerTwo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("=========================项目启动开始初始化数据-写入数据库==========================");
        System.out.println("正在写入数据库，请稍等.......");
        Thread.sleep(4000);
        log.info("=========================写入数据库-数据初始化完毕=================================");
    }
}
