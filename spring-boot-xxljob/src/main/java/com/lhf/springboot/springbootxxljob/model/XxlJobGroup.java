package com.lhf.springboot.springbootxxljob.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: XxlJobGroup
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/04/14 11:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class XxlJobGroup {

    private int id;
    private String appName;
    private String title;
    private int order;
    private int addressType;        // 执行器地址类型：0=自动注册、1=手动录入
    private String addressList;     // 执行器地址列表，多地址逗号分隔(手动录入)

    // registry list
    private List<String> registryList;  // 执行器地址列表(系统注册)
    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

}
