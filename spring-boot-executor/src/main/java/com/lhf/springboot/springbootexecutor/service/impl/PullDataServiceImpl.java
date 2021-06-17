package com.lhf.springboot.springbootexecutor.service.impl;

import com.lhf.springboot.springbootexecutor.service.PullDataService;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

/**
 * @ClassName PullDataServiceImpl
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 12:15
 **/
public class PullDataServiceImpl implements PullDataService {

    @Async("scorePoolTaskExecutor")  //加上此注解就能实现多线程
    @Override
    public Object pullDataByTime(Date time) {
        return null;
    }
}
