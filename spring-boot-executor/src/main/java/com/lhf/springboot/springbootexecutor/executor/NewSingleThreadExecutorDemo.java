package com.lhf.springboot.springbootexecutor.executor;

import com.lhf.springboot.springbootexecutor.utils.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewSingleThreadExecutorDemo
 * @Desc newSingleThreadExecutor
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * @Author liuhefei
 * @Date 2021/6/4 11:34
 **/
public class NewSingleThreadExecutorDemo {

    public void singleThreadExecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //执行任务
                    System.out.println(RandomUtils.getRandomString(10));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
