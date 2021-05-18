package com.lhf.springboot.random;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/6 11:00
 */
public class UUIDUtils {

    public static String uuid1(){
        return UUID.randomUUID().toString();
    }

    public static String uuid2(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(uuid1());

        System.out.println(uuid2());
    }
}
