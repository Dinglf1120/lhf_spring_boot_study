package com.lhf.springboot.service.impl;

import com.lhf.springboot.common.Constant;
import com.lhf.springboot.common.ResponseCode;
import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.config.RabbitConfig;
import com.lhf.springboot.mapper.MsgLogMapper;
import com.lhf.springboot.mapper.UserMapper;
import com.lhf.springboot.mq.MessageHelper;
import com.lhf.springboot.pojo.LoginLog;
import com.lhf.springboot.pojo.MsgLog;
import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import com.lhf.springboot.util.JedisUtil;
import com.lhf.springboot.util.JodaTimeUtil;
import com.lhf.springboot.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

/**
 * @ClassName: UserServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:42
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User getOne(Integer id) {
        return userMapper.selectOne(id);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public ServerResponse login(String username, String password) {

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_EMPTY.getMsg());
        }

        User user = userMapper.selectByUsernameAndPassword(username, password);
        if(null == user){
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_WRONG.getMsg());
        }

        saveAndSendMsg(user);

        return ServerResponse.success();
    }

    /**
     * 保存并发送消息
     * @param user
     */
    private void saveAndSendMsg(User user){
        String msgId = RandomUtil.UUID32();

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setType(Constant.LogType.LOGIN);

        Date date = new Date();
        loginLog.setDescription(user.getUsername() + "在" + JodaTimeUtil.dateToStr(date)+"登录系统");
        loginLog.setCreateTime(date);
        loginLog.setUpdateTime(date);
        loginLog.setMsgId(msgId);

        CorrelationData correlationData = new CorrelationData(msgId);

        rabbitTemplate.convertAndSend(RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME, MessageHelper.objToMsg(loginLog), correlationData);

        MsgLog msgLog = new MsgLog(msgId, loginLog, RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME);
        log.info("msgLog = {}", msgLog);
        msgLogMapper.insert(msgLog);  //保存消息日志
    }
}
