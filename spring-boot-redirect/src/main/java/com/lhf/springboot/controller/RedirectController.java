package com.lhf.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: RedirectController
 * @Author: liuhefei
 * @Description: 通过redirect返回String类型跳转
 * @Date: 2020/6/9 12:05
 */
@Controller
@RequestMapping("/api")
public class RedirectController {

    private final Logger logger = LoggerFactory.getLogger(RedirectController.class);

    //http://localhost:9091/api/baidu/str?keywords=meinv
    @RequestMapping("/baidu/str")
    public String baidu(@RequestParam(value = "keywords")String keywords, HttpServletRequest request, HttpServletResponse response){
        logger.info("通过redirect返回String类型跳转..................");
        String url = "redirect:https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd="+keywords;
        logger.info("url="+url);
        return url;
    }

    //重定向传参方式为get
    //http://localhost:9091/api/commit?code=2000
    @RequestMapping(value = "/commit")
    public String addUser(@RequestParam(value = "code") String code, HttpServletRequest request, HttpServletResponse response){
        String url = "redirect:http://localhost:9091/api/getResult?code="+code;  //redirect:http    不能有空格
        return url;
    }

    //http://localhost:9091/api/getResult?code=2000
    @RequestMapping("/getResult")
    @ResponseBody
    public String getResult(@RequestParam(value = "code") String code){
        if("2000".equals(code)){
            return "两个娘子小身材，\n" +
                    "\n" +
                    "捏首腰儿腿张开。";
        }else if("3000".equals(code)){
            return "两个娘子小身材，\n" +
                    "\n" +
                    "捏首腰儿腿张开。";
        }else {
            return "斜风细雨作春寒。对尊前。忆前欢。\n" +
                    "\n" +
                    "曾把梨花，寂寞泪阑干。\n" +
                    "\n" +
                    "芳草断烟南浦路，和别泪，看青山。\n" +
                    "\n" +
                    "昨宵结得梦夤缘。水云间。悄无言。\n" +
                    "\n" +
                    "争奈醒来，愁恨又依然。\n" +
                    "\n" +
                    "展转衾裯空懊恼，天易见，见伊难。";
        }

    }
}
