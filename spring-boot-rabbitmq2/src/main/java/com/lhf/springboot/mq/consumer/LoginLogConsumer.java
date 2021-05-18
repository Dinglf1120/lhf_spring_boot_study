package com.lhf.springboot.mq.consumer;

import com.lhf.springboot.mq.BaseConsumer;
import com.lhf.springboot.mq.MessageHelper;
import com.lhf.springboot.pojo.LoginLog;
import com.lhf.springboot.service.LoginLogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: LoginLogConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 17:39
 */
@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {

    @Autowired
    private LoginLogService loginLogService;


    @Override
    public void consume(Message message, Channel channel) throws IOException {
        log.info("收到消息：{}", message.toString());
        LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
        loginLogService.insert(loginLog);  //保存登录日志
    }
}
