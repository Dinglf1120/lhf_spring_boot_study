package com.lhf.springboot.springbootexecutor.test;

import com.lhf.springboot.springbootexecutor.executor.NewScheduledThreadPoolDemo;

/**
 * @ClassName NewScheduledThreadPoolTest
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 12:19
 **/
public class NewScheduledThreadPoolTest {

    public static void main(String[] args) {
        NewScheduledThreadPoolDemo newScheduledThreadPoolDemo = new NewScheduledThreadPoolDemo();
        newScheduledThreadPoolDemo.scheduleThreadPool(5);
        newScheduledThreadPoolDemo.scheduleThreadPoolTwo(6);
    }
}
