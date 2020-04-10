package com.lhf.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

/**
 * @ClassName: RedisTool1
 * @Author: liuhefei
 * @Description: 基于Redis实现分布式锁
 * @Date: 2020/3/26 17:49
 */
public class RedisTool1 {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final Long SUCCESS = 1L;

    /**
     * 获取锁
     *
     * @param lockKey  锁
     * @param value 请求标识
     * @param expireTime：单位-秒
     * @return  是否获取到锁
     */
    public boolean getLock(String lockKey, String value, int expireTime) {
        boolean ret = false;
        try {
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);

            if (SUCCESS.equals(result)) {
                return true;
            }

        } catch (Exception e) {

        }
        return ret;
    }

    /**
     * 释放锁
     *
     * @param lockKey  锁
     * @param value 请求标识
     * @return  是否成功释放锁
     */
    public boolean releaseLock(String lockKey, String value) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
        return SUCCESS.equals(result);
    }
}
