package com.lhf.springboot.service.impl;

import com.lhf.springboot.constant.Canstant;
import com.lhf.springboot.service.RedisService;
import com.lhf.springboot.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: TokenServiceImpl
 * @Author: liuhefei
 * @Description: token引用了redis服务，创建token采用随机算法工具类生成随机uuid字符串,然后放入到redis中(为了防止数据的冗余保留,
 * 这里设置过期时间为10000秒,具体可视业务而定)，如果放入成功，最后返回这个token值。
 * checkToken方法就是从header中获取token到值(如果header中拿不到，就从paramter中获取)，
 * 如若不存在,直接抛出异常。这个异常信息可以被拦截器捕捉到，然后返回给前端。
 * @Date: 2020/4/15 14:44
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     * @return
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString().replace("-", "");
        StringBuilder token = new StringBuilder();
        try{
            token.append(Canstant.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(), 100000L);
            boolean notEmpty = StringUtils.isNotEmpty(token.toString());
            if(notEmpty){
                return token.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验token
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Canstant.TOKEN_NAME);
        if(StringUtils.isBlank(token)){  //header中不存在token
            token = request.getParameter(Canstant.TOKEN_NAME);
            if(StringUtils.isBlank(token)) {
                //parameter中也不存在token
                throw new RuntimeException("parameter中token不存在");
            }
        }
        if(!redisService.exists(token)){
            throw new RuntimeException("redis中不存在token");
        }
        String tokenStr = (String) redisService.get("token");
        System.out.println("tokenStr = " + tokenStr);
        boolean remove = redisService.remove(token);
        if(!remove){
            throw new RuntimeException("token删除失败");
        }

        return true;

    }


}
