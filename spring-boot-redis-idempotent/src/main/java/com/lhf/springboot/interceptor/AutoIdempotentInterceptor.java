package com.lhf.springboot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lhf.springboot.constant.AutoIdempotent;
import com.lhf.springboot.service.TokenService;
import com.lhf.springboot.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @ClassName: AutoIdempotentInterceptor
 * @Author: liuhefei
 * @Description: 拦截器
 * 拦截处理器：主要的功能是拦截扫描到AutoIdempotent到注解到方法,然后调用tokenService的checkToken()方法校验token是否正确，
 * 如果捕捉到异常就将异常信息渲染成json返回给前端
 * @Date: 2020/4/15 15:23
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;


   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

       if (!(handler instanceof HandlerMethod)) {
           return true;
       }
       HandlerMethod handlerMethod = (HandlerMethod) handler;
       Method method = handlerMethod.getMethod();
       //被ApiIdempotment标记的扫描
       AutoIdempotent autoIdempotent = method.getAnnotation(AutoIdempotent.class);
       if(autoIdempotent != null){
           try{
               return tokenService.checkToken(request);  //幂等性校验，校验通过则放行，校验失败则抛出异常, 并通过统一异常处理返回友好提示
           }catch (Exception e){
               ResultVo resultVo = new ResultVo(101, e.getMessage());
               writeReturnJson(response, JSONObject.toJSONString(resultVo));
               throw e;
           }
       }
       //必须返回true,否则会被拦截一切请求
       return true;
   }

    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try{
            writer = response.getWriter();
            writer.print(json);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
