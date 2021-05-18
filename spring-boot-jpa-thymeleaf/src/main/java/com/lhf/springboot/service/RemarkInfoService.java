package com.lhf.springboot.service;

import com.lhf.springboot.common.Pager;
import com.lhf.springboot.entity.RemarkInfo;

import java.util.Map;

/**
 * @ClassName: RemarkInfoService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/2 10:14
 */
public interface RemarkInfoService {

    public Pager<RemarkInfo> findAll(Map<String, String> map, int size) throws Exception;

    public void saveRemarkInfo(Map<String, String> map) throws Exception;

    public void deteleRemarkInfoById(int id) throws Exception;


}
