package com.lhf.springboot.service;

import com.lhf.springboot.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:28
 */
public interface TokenService {

    /**
     * 创建token
     * @return
     */
    ServerResponse createToken();

    /**
     * 验证token
     * @param request
     */
    void checkToken(HttpServletRequest request);
}
