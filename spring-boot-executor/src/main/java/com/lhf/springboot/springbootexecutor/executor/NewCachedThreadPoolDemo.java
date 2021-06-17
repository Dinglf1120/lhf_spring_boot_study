package com.lhf.springboot.springbootexecutor.executor;

import com.lhf.springboot.springbootexecutor.utils.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewCachedThreadPoolDemo
 * @Desc 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * @Author liuhefei
 * @Date 2021/5/18 16:25
 **/
public class NewCachedThreadPoolDemo {

    public void cachedThreadPool(){
        //创建线程池
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++){
            final int n = 1;
            try {
                Thread.sleep(n * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //执行任务
            //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
            cachedPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n + "-" + Thread.currentThread().getName());
                    System.out.println(RandomUtils.getRandomString(20));
                }
            });

        }
    }

}
