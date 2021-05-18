package com.lhf.springboot.springbootxxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName: OnceJobHandler
 * @Author: liuhefei
 * @Description: TODD
 */
@JobHandler(value="onceJobHandler")
@Service
public class OnceJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("onceJobHandler 执行了 " + LocalDateTime.now());
        System.out.println("参数：" + s);
        XxlJobLogger.log("XXL-JOB 执行一次。。。");

        return SUCCESS;
    }
}
