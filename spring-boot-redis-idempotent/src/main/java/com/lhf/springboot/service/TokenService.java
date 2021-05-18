package com.lhf.springboot.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenService
 * @Author: liuhefei
 * @Description: Token服务接口
 * @Date: 2020/4/15 14:41
 */
public interface TokenService {

    /**
     * 创建Token
     * 生成一个字符串
     * @return
     */
    public String createToken();

    /**
     * 检验Token
     * 获取header里面的token,然后检验，通过抛出的Exception来获取具体的报错信息返回给前端。
     * @param request
     * @return
     * @throws Exception
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;
}
