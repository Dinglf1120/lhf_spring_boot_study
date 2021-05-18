package com.lhf.springboot.service;

import com.lhf.springboot.pojo.LoginLog;

/**
 * @ClassName: LoginLogService
 * @Author: liuhefei
 * @Description: 登录日志
 * @Date: 2020/4/16 10:27
 */
public interface LoginLogService {

    //添加
    void insert(LoginLog loginLog);

    //根据msgId查询
    LoginLog selectByMsgId(String msgId);
}
