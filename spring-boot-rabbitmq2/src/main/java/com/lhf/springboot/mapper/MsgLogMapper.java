package com.lhf.springboot.mapper;

import com.lhf.springboot.pojo.MsgLog;

import java.util.List;

/**
 * @ClassName: MsgLogMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 11:12
 */
public interface MsgLogMapper {

    void insert(MsgLog msgLog);

    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

}
