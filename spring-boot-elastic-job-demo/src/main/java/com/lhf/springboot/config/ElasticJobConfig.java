package com.lhf.springboot.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.lhf.springboot.job.SimpleJobDemo;
import com.lhf.springboot.job.SimpleJobDemo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ElasticJobConfig
 * @Author: liuhefei
 * @Description: 配置zookeeper注册中心
 * @Date: 2020/4/14 16:53
 */
@Configuration
public class ElasticJobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    /**
     * 配置任务监听器
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener(){
        return new MyElasticJobListener();
    }

    /**
     * 配置任务详细信息
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters){
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                        .shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())
        ).overwrite(true).build();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJobDemo simpleJob,
                                           @Value("${stockJob.cron}") final String cron,
                                           @Value("${stockJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${stockJob.shardingItemParameters}") final String shardingItemParameters) {
        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
        return new SpringJobScheduler(simpleJob, regCenter,
                getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters),
                elasticJobListener);
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler1(final SimpleJobDemo1 simpleJob1,
                                           @Value("${stockJob.cron1}") final String cron,
                                           @Value("${stockJob.shardingTotalCount1}") final int shardingTotalCount,
                                           @Value("${stockJob.shardingItemParameters1}") final String shardingItemParameters) {
        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
        return new SpringJobScheduler(simpleJob1, regCenter,
                getLiteJobConfiguration(simpleJob1.getClass(), cron, shardingTotalCount, shardingItemParameters),
                elasticJobListener);
    }

}
