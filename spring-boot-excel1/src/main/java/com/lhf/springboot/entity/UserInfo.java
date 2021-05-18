package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: UserInfo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/28 17:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfo {

    private Integer id;

    private String username;

    private Integer age;

    private String sex;

    private Integer height;

    private String cupSize;

    private String modile;
}
