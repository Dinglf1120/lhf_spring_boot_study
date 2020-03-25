package com.lhf.springboot.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName: RestTemplateUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/12/3 19:49
 */
@Component
public class RestTemplateUtil {

    private RestTemplate restTemplate;

    /**
     * 发送GET请求,参数为Map类型
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return
     */
    public String GetData(String url, Map<String, String> param) {

        restTemplate = new RestTemplate();
        // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return restTemplate.getForEntity(url, String.class, param).getBody();
    }

    /**
     * 发送POST-JSON请求，参数为Json类型
     *
     * @param url
     * @param param
     * @return
     */
    public String PostJsonData(String url, JSONObject param) {

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(param, headers);
        return restTemplate.postForEntity(url, param, String.class).getBody();
    }

    /**
     * 发送POST 表单请求
     *
     * @param url
     * @param param
     * @return
     */
    public String PostFormData(String url, MultiValueMap<String, String> param) {

        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return restTemplate.postForEntity(url, param, String.class).getBody();
    }
}
