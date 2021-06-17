package com.lhf.springboot.springbootexecutor.executor;

import com.lhf.springboot.springbootexecutor.utils.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewFixedThreadPoolDemo
 * @Desc 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * @Author liuhefei
 * @Date 2021/5/18 16:36
 **/
public class NewFixedThreadPoolDemo {

    public void fixedThreadPool(){
        //创建线程池
        ExecutorService fixedPool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++){
            final  int n = 1;

            //定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
            fixedPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(n + " - " + Thread.currentThread().getName());
                        System.out.println(RandomUtils.getRandomString(5));
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }
}
