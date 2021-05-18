package com.lhf.springboot.service;

import com.lhf.springboot.pojo.MsgLog;

import java.util.List;
import java.util.Date;

/**
 * @ClassName: MsgLogService
 * @Author: liuhefei
 * @Description: 消息日志
 * @Date: 2020/4/16 10:27
 */
public interface MsgLogService {

    //更新状态
    void updateStatus(String msgId, Integer status);

    //根据msgId查询
    MsgLog selectByMsgId(String msgId);

    //获取消息日志列表
    List<MsgLog> selectTimeoutMsg();

    //更新重试次数
    void updateTryCount(String msgId, Date tryTime);
}
