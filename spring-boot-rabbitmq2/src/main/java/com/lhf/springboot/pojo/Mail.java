package com.lhf.springboot.pojo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: Mail
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 17:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Mail {
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String to;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "正文不能为空")
    private String content;

    private String msgId;// 消息id

}
