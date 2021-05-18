package com.lhf.springboot.springbootxxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName: HelloJobHandler
 * @Author: liuhefei
 * @Description: TODD
 */
@JobHandler(value="helloJobHandler")
@Service
public class HelloJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {

        System.out.println("xxl-job 执行成功！ hi,你好！Hello World." + LocalDateTime.now());

        XxlJobLogger.log("XXL-JOB, hi,你好！Hello World.");

        return SUCCESS;
    }
}
