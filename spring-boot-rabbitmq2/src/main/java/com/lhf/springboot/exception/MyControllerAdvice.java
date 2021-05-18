package com.lhf.springboot.exception;

import com.lhf.springboot.common.ResponseCode;
import com.lhf.springboot.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: MyControllerAdvice
 * @Author: liuhefei
 * @Description: 控制类异常处理
 * @Date: 2020/4/15 18:56
 */
@ControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ServerResponse serverExceptionHandler(ServiceException se){
        return ServerResponse.error(se.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ServerResponse exceptionHandler(Exception e){
        log.error("Exception : ", e);
        return ServerResponse.error(ResponseCode.SERVER_ERROR.getMsg());
    }
}
