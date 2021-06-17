package com.lhf.springboot.service.impl;

import com.lhf.springboot.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName AsyncServiceImpl
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 14:46
 **/
@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {

        logger.info("start executeAsync");

        for(int i = 1; i <= 1000; i++){
            System.out.println(i + " - 异步线程要做的事情");
            System.out.println(i + " - 可以在这里执行批量插入等耗时的事情");
        }

        logger.info("end executeAsync");

    }
}
