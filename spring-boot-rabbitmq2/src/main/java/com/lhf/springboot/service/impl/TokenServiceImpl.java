package com.lhf.springboot.service.impl;

import com.lhf.springboot.common.Constant;
import com.lhf.springboot.common.ResponseCode;
import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.exception.ServiceException;
import com.lhf.springboot.service.TokenService;
import com.lhf.springboot.util.JedisUtil;
import com.lhf.springboot.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:42
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32();
        StrBuilder token = new StrBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);

        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);
        log.info("create token = {}", token.toString());

        return ServerResponse.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if(StringUtils.isBlank(token)){   //header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if(StringUtils.isBlank(token)){  //parameter中也不存在token
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        if(!jedisUtil.exists(token)){  //redis中不存在
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        log.info("token = {}", token);
        //删除token
        Long del = jedisUtil.del(token);
        if(del <= 0){
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        log.info("token 删除成功..........");

    }
}
