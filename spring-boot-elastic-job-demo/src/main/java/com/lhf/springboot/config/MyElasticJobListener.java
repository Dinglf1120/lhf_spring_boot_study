package com.lhf.springboot.config;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.lhf.springboot.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: MyElasticJobListener
 * @Author: liuhefei
 * @Description: 任务总监听器
 * @Date: 2020/4/14 17:42
 */
public class MyElasticJobListener implements ElasticJobListener {

    private static final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);

    private long beginTime = 0;


    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();

        logger.info("===>{} JOB BEGIN TIME: {} <===",shardingContexts.getJobName(), TimeUtil.mill2Time(beginTime));
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        logger.info("===>{} JOB END TIME: {},TOTAL CAST: {} <===",shardingContexts.getJobName(), TimeUtil.mill2Time(endTime), endTime - beginTime);
    }
}
