package com.lhf.springboot.pojo;

import lombok.*;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 18:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;
}
