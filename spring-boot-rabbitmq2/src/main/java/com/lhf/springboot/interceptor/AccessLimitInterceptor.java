package com.lhf.springboot.interceptor;

import com.lhf.springboot.annotation.AccessLimit;
import com.lhf.springboot.common.Constant;
import com.lhf.springboot.common.ResponseCode;
import com.lhf.springboot.exception.ServiceException;
import com.lhf.springboot.util.IpUtil;
import com.lhf.springboot.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName: AccessLimitInterceptor
 * @Author: liuhefei
 * @Description: 接口防刷限流拦截器
 * @Date: 2020/4/15 18:22
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(!(handler instanceof HandlerMethod)){
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
        if(accessLimit != null){
            check(accessLimit, request);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    private void check(AccessLimit accessLimit, HttpServletRequest request){
        int maxCount = accessLimit.maxCount();
        int seconds = accessLimit.seconds();

        StringBuilder sb = new StringBuilder();
        sb.append(Constant.Redis.ACCESS_LIMIT_PREFIX).append(IpUtil.getIpAddress(request)).append(request.getRequestURI());
        String key = sb.toString();

        Boolean exists = jedisUtil.exists(key);
        if(!exists){
            jedisUtil.set(key, String.valueOf(1), seconds);
        }else {
            int count = Integer.valueOf(jedisUtil.get(key));
            if(count < maxCount){
                Long ttl = jedisUtil.ttl(key);
                if(ttl <= 0){
                    jedisUtil.set(key, String.valueOf(1), seconds);
                }else {
                    jedisUtil.set(key, String.valueOf(++count), ttl.intValue());
                }
            }else {
                throw new ServiceException(ResponseCode.ACCESS_LIMIT.getMsg());
            }
        }
    }
}
