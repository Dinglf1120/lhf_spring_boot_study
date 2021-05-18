package com.lhf.springboot.mq.consumer;

import com.lhf.springboot.exception.ServiceException;
import com.lhf.springboot.mq.BaseConsumer;
import com.lhf.springboot.mq.MessageHelper;
import com.lhf.springboot.pojo.Mail;
import com.lhf.springboot.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: MailConsumer
 * @Author: liuhefei
 * @Description: 消费消息，发送邮件
 * @Date: 2020/4/16 17:25
 */
@Component
@Slf4j
public class MailConsumer  implements BaseConsumer {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息：{}", mail.toString());

        boolean success = mailUtil.send(mail);
        if(!success){
            throw new ServiceException("send mail error");
        }
    }
}
