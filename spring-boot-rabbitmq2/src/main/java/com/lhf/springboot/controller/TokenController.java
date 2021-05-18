package com.lhf.springboot.controller;

import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 18:13
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    //http://localhost:9005/token/create
    @GetMapping("/create")
    public ServerResponse token(){
        return tokenService.createToken();
    }

    //http://localhost:9005/token/check
    @PostMapping("/check")
    public ServerResponse checkToken(HttpServletRequest request){

        tokenService.checkToken(request);
        return ServerResponse.success();
    }
}
