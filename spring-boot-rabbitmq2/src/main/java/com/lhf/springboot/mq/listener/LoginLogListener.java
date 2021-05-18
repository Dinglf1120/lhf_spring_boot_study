package com.lhf.springboot.mq.listener;

import com.lhf.springboot.config.RabbitConfig;
import com.lhf.springboot.mq.BaseConsumer;
import com.lhf.springboot.mq.BaseConsumerProxy;
import com.lhf.springboot.mq.consumer.LoginLogConsumer;
import com.lhf.springboot.service.MsgLogService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: LoginLogListener
 * @Author: liuhefei
 * @Description: 登录日志监听器
 * @Date: 2020/4/16 17:05
 */
@Component
public class LoginLogListener {

    @Autowired
    private LoginLogConsumer loginLogConsumer;

    @Autowired
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.LOGIN_LOG_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException{
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(loginLogConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if(null != proxy){
            proxy.consume(message, channel);
        }
    }

}
