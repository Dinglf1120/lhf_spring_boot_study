package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: PageData
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/28 15:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageData {

    //订单id
    private String ordersn;

    //订单金额
    private Double totalprice;

    //收货人
    private String consignee;

    //手机号码
    private String mobile;

    //省份
    private String provincename;

    //地址
    private String address;


}
