package com.lhf.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ModelAndViewController
 * @Author: liuhefei
 * @Description: 通过ModelAndView进行重定向
 * @Date: 2020/6/9 11:10
 */
@RestController
@RequestMapping(("/api"))
public class ModelAndViewController {

    private final Logger logger = LoggerFactory.getLogger(ModelAndViewController.class);

    //http://localhost:9091/api/baidu?keywords=meinv
    @RequestMapping("/baidu")
    public ModelAndView sousuo(@RequestParam(value = "keywords")String keywords, HttpServletRequest request, HttpServletResponse response){

        logger.info("百度关键词搜索............." + keywords);
        String url = "redirect:https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd="+keywords;
        logger.info("url = " + url);
        String requestUrl = request.getRequestURI();
        logger.info("requestUrl = " + requestUrl);
        String contentType = response.getContentType();
        logger.info("contentType = " + contentType);
        return new ModelAndView(url);
    }
}
