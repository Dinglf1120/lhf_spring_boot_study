package com.lhf.springboot.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RedissonConfig
 * @Author: liuhefei
 * @Description: Redisson配置
 * @Date: 2020/4/16 9:49
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    //@Value("${spring.redis.password}")
    //private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //密码不为空的情况下
        //config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //密码为空
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }

}

