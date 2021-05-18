package com.lhf.springboot.mapper;

import com.lhf.springboot.pojo.LoginLog;

/**
 * @ClassName: LoginLogMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 11:11
 */
public interface LoginLogMapper {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
