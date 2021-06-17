package com.lhf.springboot.springbootexecutor.test;

import com.lhf.springboot.springbootexecutor.executor.NewCachedThreadPoolDemo;

/**
 * @ClassName NewCachedThreadPoolTest
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 12:18
 **/
public class NewCachedThreadPoolTest {

    public static void main(String[] args) {
        NewCachedThreadPoolDemo newCachedThreadPoolDemo = new NewCachedThreadPoolDemo();
        newCachedThreadPoolDemo.cachedThreadPool();
    }
}
