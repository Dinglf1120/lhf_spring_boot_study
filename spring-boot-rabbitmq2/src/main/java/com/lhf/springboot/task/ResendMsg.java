package com.lhf.springboot.task;

import com.lhf.springboot.common.Constant;
import com.lhf.springboot.mq.MessageHelper;
import com.lhf.springboot.pojo.MsgLog;
import com.lhf.springboot.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: ResendMsg
 * @Author: liuhefei
 * @Description: 定时任务重新投递发送失败的消息
 * 每一条消息都和exchange routingKey绑定, 所有消息重投共用这一个定时任务即可。
 * @Date: 2020/4/16 9:32
 */
@Component
@Slf4j
public class ResendMsg {

    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //最大投递次数
    private static final int MAX_TRY_COUNT = 3;

    /**
     * 每隔30s拉取投递失败的消息，重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend(){
        log.info("开始执行定时任务（重新投递消息）");

        List<MsgLog> msgLogList = msgLogService.selectTimeoutMsg();
        msgLogList.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if(msgLog.getTryCount() >= MAX_TRY_COUNT){
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("超过最大重试次数，消息投递失败， msgId: {}", msgId);
            }else {
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());   //投递次数加1

                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(), msgLog.getRoutingKey(), MessageHelper.objToMsg(msgLog.getMsg()), correlationData); // 重新投递

                log.info("第" + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });
        log.info("定时任务执行结束（重新投递消息）");
    }

}
