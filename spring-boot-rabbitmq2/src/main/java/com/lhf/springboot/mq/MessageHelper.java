package com.lhf.springboot.mq;

import com.lhf.springboot.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

/**
 * @ClassName: MessageHelper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 16:20
 */
public class MessageHelper {

    public static Message objToMsg(Object obj){
        if(null == obj){
            return null;
        }

        Message message = MessageBuilder.withBody(JsonUtil.objToStr(obj).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); //消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);

        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz){
        if(null == message || null == clazz){
            return null;
        }

        String str = new String(message.getBody());
        T obj = JsonUtil.strToObj(str, clazz);

        return obj;
    }
}
