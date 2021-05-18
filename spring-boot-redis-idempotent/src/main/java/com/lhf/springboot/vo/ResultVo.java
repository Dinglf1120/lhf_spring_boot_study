package com.lhf.springboot.vo;

/**
 * @ClassName: ResultVo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/15 15:37
 */
public class ResultVo {

    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVo() {
    }

    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
