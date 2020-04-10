package com.lhf.springboot.springbootrunner.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: InitDataRunnerOne
 * @Author: liuhefei
 * @Description: 实现CommandLineRunner接口实现项目启动时初始化数据
 * @Date: 2020/4/10 12:03
 */
@Slf4j
@Component
public class InitDataRunnerOne implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("=========================项目启动开始初始化数据-查询数据==========================");
        System.out.println("正在初始化数据，请稍等.......");
        Thread.sleep(4000);
        log.info("=========================查询数据-数据初始化完毕=================================");
    }
}
