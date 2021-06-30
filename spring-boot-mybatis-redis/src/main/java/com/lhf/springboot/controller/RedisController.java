package com.lhf.springboot.controller;

import com.lhf.springboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedisController
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/24 21:51
 **/
@RestController
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("setAndGet")
    public String test(String k,String v){
        redisUtils.set(k,v);
        return (String) redisUtils.get(k);
    }
}