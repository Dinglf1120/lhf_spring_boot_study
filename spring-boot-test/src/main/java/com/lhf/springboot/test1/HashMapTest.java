package com.lhf.springboot.test1;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HashMapTest
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/7 20:15
 **/
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("刘备", "领导");
        hashMap.put("关羽", "将军");

        String value = hashMap.get("刘备");
        System.out.println(value);
        System.out.println("刘备".hashCode());
        System.out.println("刘备".hashCode() % 8);

        //解决hash冲突的办法：1.再散列法 2.链表法

        System.out.println(Integer.highestOneBit(14));
        System.out.println(Integer.highestOneBit(15));
        System.out.println(Integer.highestOneBit(16));

        //设置HashMap的大小以及加载因子
        HashMap<String, String> hashMapData = new HashMap<>(32, 1);

        //Java1.8 默认情况下，当链表长度超过8时，转换为红黑树



    }

}
