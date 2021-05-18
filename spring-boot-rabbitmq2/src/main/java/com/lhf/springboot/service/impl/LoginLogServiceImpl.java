package com.lhf.springboot.service.impl;

import com.lhf.springboot.mapper.LoginLogMapper;
import com.lhf.springboot.pojo.LoginLog;
import com.lhf.springboot.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoginLogServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:41
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void insert(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Override
    public LoginLog selectByMsgId(String msgId) {
        return loginLogMapper.selectByMsgId(msgId);
    }
}
