package com.lhf.springboot.springbootxxljob.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: XxlJobGroupVo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/04/14 11:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class XxlJobGroupVo {

    private Integer id;  //执行器id，必填

    private Integer type;  //操作类型参数：0:添加， 1:更新

    private String appName; //app名称

    private String title;  //执行器名称

    private Integer order;  //执行器排序

    private Integer addressType;  //执行器地址类型：0=自动注册、1=手动录入

    private String addressList; //执行器地址列表，多地址逗号分隔(手动录入时必填)

}
