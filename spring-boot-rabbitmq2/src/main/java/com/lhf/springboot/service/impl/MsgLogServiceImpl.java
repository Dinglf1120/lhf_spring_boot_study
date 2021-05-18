package com.lhf.springboot.service.impl;

import com.lhf.springboot.mapper.MsgLogMapper;
import com.lhf.springboot.pojo.MsgLog;
import com.lhf.springboot.service.MsgLogService;
import com.lhf.springboot.util.JodaTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MsgLogServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:41
 */
@Service
public class MsgLogServiceImpl implements MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

        MsgLog msgLog = new MsgLog();
        msgLog.setMsg(msgId);
        msgLog.setNextTryTime(nextTryTime);

        msgLogMapper.updateTryCount(msgLog);

    }
}
