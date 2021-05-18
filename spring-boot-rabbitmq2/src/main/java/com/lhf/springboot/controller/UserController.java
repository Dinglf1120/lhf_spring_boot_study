package com.lhf.springboot.controller;

import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 19:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //http://localhost:9005/user/users
    @GetMapping("users")
    public String getAll(){
        List<User> userList = userService.getAll();
        return userList.toString();
    }

    //http://localhost:9005/user/4
    @GetMapping("{id}")
    public String getOne(@PathVariable Integer id){
        User user = userService.getOne(id);
        logger.info("user = " + user);
        if(null != user){
            return user.toString();
        }else {
            return "not exists";
        }
    }

    //http://localhost:9005/user/?username=test&password=123456
    @PostMapping
    public String add(User user){
        userService.add(user);
        return "success";
    }

    @PutMapping
    public String update(User user) {
        userService.update(user);
        return "nice";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "nice";
    }

    //http://localhost:9005/user/login?username=admin&password=123456
    @PostMapping("/login")
    public ServerResponse login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        logger.info("入参username = " + username + " , password = " + password);
        return userService.login(username, password);
    }
}
