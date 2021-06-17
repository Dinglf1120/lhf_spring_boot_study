package com.lhf.springboot.springbootexecutor.test;

import com.lhf.springboot.springbootexecutor.executor.NewSingleThreadExecutorDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewSingleThreadExecutorTest
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 12:19
 **/
public class NewSingleThreadExecutorTest {

    public static void main(String[] args) {
        NewSingleThreadExecutorDemo newSingleThreadExecutorDemo = new NewSingleThreadExecutorDemo();
        newSingleThreadExecutorDemo.singleThreadExecutor();

        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        //创建实现了Runnable接口对象
        Thread t1 = new MyThread();

        Thread t2 = new MyThread();

        Thread t3 = new MyThread();

        Thread t4 = new MyThread();

        Thread t5 = new MyThread();

        //将线程放入池中进行执行

        pool.execute(t1);

        pool.execute(t2);

        pool.execute(t3);

        pool.execute(t4);

        pool.execute(t5);

        //关闭线程池
        pool.shutdown();


    }
}
