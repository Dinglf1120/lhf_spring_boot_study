package com.lhf.springboot.mapper;

import com.lhf.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 11:12
 */
public interface UserMapper {

    List<User> selectAll();

    User selectOne(Integer id);

    void insert(User user);

    void update(User user);

    void delete(Integer id);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password")String passwors);
}
