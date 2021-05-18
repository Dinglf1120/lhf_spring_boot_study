package com.lhf.springboot.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: LoginLog
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 17:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 5131733720521277367L;

    private Integer id;
    private Integer userId;  //用户id
    private Integer type;   //日志类型:1登录 2登出
    private String description;   //日志描述
    private Date createTime;
    private Date updateTime;
    private String msgId;// 消息id
}
