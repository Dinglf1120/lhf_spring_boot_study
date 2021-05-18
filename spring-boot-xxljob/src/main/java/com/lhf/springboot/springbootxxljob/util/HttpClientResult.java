package com.lhf.springboot.springbootxxljob.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @ClassName: HttpClientResult
 * @Author: liuhefei
 * @Description: 封装http响应结果
 * @Date: 2020/04/14 16:44
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }


    public HttpClientResult(int code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
