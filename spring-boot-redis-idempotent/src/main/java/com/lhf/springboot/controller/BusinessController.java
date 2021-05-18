package com.lhf.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.lhf.springboot.service.TokenService;
import com.lhf.springboot.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BusinessController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 15:55
 */
@RestController
public class BusinessController {
    @Resource
    private TokenService tokenService;

    @PostMapping("/get/token")
    public String getToken(){
        String token = tokenService.createToken();
        System.out.println("token = " + token);
        if(StringUtils.isNotEmpty(token)){
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(1);
            resultVo.setMessage("SUCCESS");
            resultVo.setData(token);
            System.out.println("resultVo = " + JSONObject.toJSONString(resultVo));
            return JSONObject.toJSONString(resultVo);
        }

        return null;
    }

    @PostMapping("/checkToken")
    public String checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println("token = " + token);
        ResultVo resultVo = new ResultVo();
        try{
           if(StringUtils.isNotEmpty(token)){
               boolean bool = tokenService.checkToken(request);
               if(bool){
                   resultVo.setCode(1);
                   resultVo.setMessage("SUCCESS");
                   resultVo.setData(token);
                   System.out.println("resultVo = " + JSONObject.toJSONString(resultVo));
                   return JSONObject.toJSONString(resultVo);
               }else {
                   resultVo.setCode(-1);
                   resultVo.setMessage("fail");
                   resultVo.setData("");
                   System.out.println("resultVo = " + JSONObject.toJSONString(resultVo));
                   return JSONObject.toJSONString(resultVo);
               }
           }
        }catch (Exception e){
            resultVo.setCode(-1);
            resultVo.setMessage(e.getMessage());
            resultVo.setData("");
            System.out.println("resultVo = " + JSONObject.toJSONString(resultVo));
            return JSONObject.toJSONString(resultVo);
        }
        return null;
    }
}
