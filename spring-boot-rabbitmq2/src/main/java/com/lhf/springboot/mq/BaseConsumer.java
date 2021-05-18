package com.lhf.springboot.mq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @ClassName: BaseConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 16:20
 */
public interface BaseConsumer {

    void consume(Message message, Channel channel) throws IOException;
}
