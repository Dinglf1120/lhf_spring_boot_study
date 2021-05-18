package com.lhf.springboot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SimpleJobDemo1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/14 17:57
 */
//@ElasticSimpleJob(cron="*/4 * * * * ?", shardingTotalCount=2)
@Component
public class SimpleJobDemo1 implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()){
            case 0:
                System.out.println(shardingContext.getShardingItem() + "--" + shardingContext.getJobName() + "执行了.... 数据写入0号库成功");
                break;
            case 1:
                System.out.println(shardingContext.getShardingItem() + "--" + shardingContext.getJobName() + "执行了.... 数据写入1号库成功");
                break;

        }
    }
}
