package com.lhf.springboot.springbootexecutor.executor;

import com.lhf.springboot.springbootexecutor.utils.RandomUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NewScheduledThreadPoolDemo
 * @Desc 创建一个定长线程池，支持定时任务及周期性任务执行。
 * @Author liuhefei
 * @Date 2021/5/18 16:44
 **/
public class NewScheduledThreadPoolDemo {

    public void scheduleThreadPool(int index){
        //创建线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(RandomUtils.getRandomString(16));
            }
        }, 4, TimeUnit.SECONDS);   //延迟4秒执行一次
    }


    public void scheduleThreadPoolTwo(int index){
        //创建线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(RandomUtils.getRandomString(16));
            }
        }, 5, 4, TimeUnit.SECONDS);   //延迟5秒后，每隔4秒执行一次
    }

}
