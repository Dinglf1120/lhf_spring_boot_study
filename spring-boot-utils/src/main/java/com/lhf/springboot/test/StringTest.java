package com.lhf.springboot.test;

/**
 * @ClassName StringTest
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/4/17 11:30
 **/
public class StringTest {

    public static void main(String[] args) {
        String roleIdStr = "1,2,3,4,5,6,7,8,9";
        String[] roleIdArr = roleIdStr.split(",");
        Long roleId = null;
        for(int i = 0; i < roleIdArr.length; i++){
            roleId = Long.parseLong(roleIdArr[i]);
            System.out.println("roleId = " + roleId);
        }

    }
}
