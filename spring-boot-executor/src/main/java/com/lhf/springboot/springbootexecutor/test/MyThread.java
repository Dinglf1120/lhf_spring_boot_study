package com.lhf.springboot.springbootexecutor.test;

/**
 * @ClassName MyThread
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 12:22
 **/
public class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "正在执行....");
    }
}
