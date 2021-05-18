package com.lhf.springboot.service;

import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.pojo.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:28
 */
public interface UserService {

    //获取所有用户信息
    List<User> getAll();

    //根据id获取用户信息
    User getOne(Integer id);

    //添加用户
    void add(User user);

    //更新用户
    void update(User user);

    //删除用户
    void delete(Integer id);

    //获取用户信息
    User getByUsernameAndPassword(String username, String password);

    //用户登录
    ServerResponse login(String username, String password);
}
