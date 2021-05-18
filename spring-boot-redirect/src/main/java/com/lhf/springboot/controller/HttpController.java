package com.lhf.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: HttpController
 * @Author: liuhefei
 * @Description: 通过HttpServletResponse跳转
 * @Date: 2020/6/9 11:50
 */
@RestController
@RequestMapping("/api")
public class HttpController {

    private final Logger logger = LoggerFactory.getLogger(HttpController.class);

    //http://localhost:9091/api/baidu/meinv
    @RequestMapping("/baidu/{keywords}")
    public void baidu(@PathVariable("keywords")String keywords, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("利用HttpServletResponse跳转请求.................");
        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=" + keywords;

        response.setHeader("name", "乾坤未定，你我皆是黑马");
        response.sendRedirect(url);
        String contentType = response.getContentType();
        String headers = response.getHeader("name");
        int status = response.getStatus();
        logger.info("contentType=" +contentType +"\nheaders=" + headers + "\nstatus=" + status );
    }
}
