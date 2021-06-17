package com.lhf.springboot.controller;

import com.lhf.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ThreadPoolController
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/4 14:53
 **/
@RequestMapping("/boot")
@RestController
public class ThreadPoolController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }
}
