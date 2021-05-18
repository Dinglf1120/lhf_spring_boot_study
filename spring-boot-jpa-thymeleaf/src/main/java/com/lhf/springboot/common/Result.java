package com.lhf.springboot.common;

import lombok.Data;

/**
 * @ClassName: Result
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/3 15:19
 */
@Data
public class Result {

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;

    public Result(){

    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
