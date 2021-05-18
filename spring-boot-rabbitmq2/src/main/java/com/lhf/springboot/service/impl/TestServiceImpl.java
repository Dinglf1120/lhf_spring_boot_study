package com.lhf.springboot.service.impl;

import com.lhf.springboot.common.ResponseCode;
import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.config.RabbitConfig;
import com.lhf.springboot.mapper.MsgLogMapper;
import com.lhf.springboot.mq.MessageHelper;
import com.lhf.springboot.pojo.Mail;
import com.lhf.springboot.pojo.MsgLog;
import com.lhf.springboot.service.TestService;
import com.lhf.springboot.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TestServiceImpl
 * @Author: liuhefei
 * @Description: 生产消息
 * @Date: 2020/4/16 10:42
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);  //消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData); //发送消息

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }
}
