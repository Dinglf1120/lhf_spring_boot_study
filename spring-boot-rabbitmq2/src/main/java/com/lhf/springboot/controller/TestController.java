package com.lhf.springboot.controller;

import com.lhf.springboot.annotation.AccessLimit;
import com.lhf.springboot.annotation.ApiIdempotent;
import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.pojo.Mail;
import com.lhf.springboot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 18:07
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    //保证 接口幂等性
    //http://localhost:9005/test/testIdempotence
    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence(){
        return testService.testIdempotence();
    }

    //保证  接口防刷限流
    @AccessLimit(maxCount = 5, seconds = 5)
    @PostMapping("accessLimit")
    public ServerResponse accessLimit(){
        return testService.accessLimit();
    }

    @PostMapping("send")
    public ServerResponse sendMail(@Validated Mail mail, Errors errors){
        if(errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }

        return testService.send(mail);

    }
}
