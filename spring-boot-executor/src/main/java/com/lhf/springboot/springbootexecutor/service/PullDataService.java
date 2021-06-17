package com.lhf.springboot.springbootexecutor.service;

import java.util.Date;

/**
 * @ClassName PullDataService
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 11:42
 **/
public interface PullDataService {

    //根据时间拉取指定范围内的数据   这只是一个demo
    Object pullDataByTime(Date time);
}
