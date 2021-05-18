package com.lhf.springboot.pojo;

import com.lhf.springboot.common.Constant;
import com.lhf.springboot.util.JodaTimeUtil;
import com.lhf.springboot.util.JsonUtil;
import lombok.*;

import java.util.Date;

/**
 * @ClassName: MsgLog
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 17:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MsgLog {

    private String msgId;    //消息唯一标识
    private String msg;        //消息体, json格式化
    private String exchange;    //交换机
    private String routingKey;  //路由键
    private Integer status;   //状态: 0投递中 1投递成功 2投递失败 3已消费
    private Integer tryCount;  //重试次数
    private Date nextTryTime;   //下一次重试时间
    private Date createTime;
    private Date updateTime;

    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
