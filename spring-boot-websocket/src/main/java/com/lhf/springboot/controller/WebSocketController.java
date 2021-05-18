package com.lhf.springboot.controller;

import com.lhf.springboot.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebSocketController
 * @Desc 消息推送控制器
 * @Author liuhefei
 * @Date 2021/5/8 15:42
 **/
@Controller("web_Scoket_system")
@RequestMapping("/api/socket")
public class WebSocketController {

    //http://localhost:8082/api/socket/index
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        System.out.println("---------------");
        mv.setViewName("index");
        return mv;
    };

    //页面请求  http://localhost:8082/api/socket/index/123
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("/index");
        mav.addObject("userId", userId);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Map pushToWeb(@PathVariable String cid, String message) {
        Map<String,Object> result = new HashMap<>();
        try {
            WebSocketServer.sendInfo(message, cid);
            result.put("code", cid);
            result.put("msg", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
