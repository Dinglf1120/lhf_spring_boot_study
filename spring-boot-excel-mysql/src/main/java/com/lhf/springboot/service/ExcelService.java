package com.lhf.springboot.service;

import com.lhf.springboot.entity.DictumBaseInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName: ExcelService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/29 10:19
 */
public interface ExcelService {

    /**
     *上传excel文件到临时目录后并开始解析
     * @param fileName  文件名
     * @param mfile 文件
     * @param userName
     * @return
     */
    public String batchImport(String fileName, MultipartFile mfile, String userName);

    /**
     * 保存数据
     * @param dictumBaseInfo
     * @param userName
     */
    public void saveDictumBaseInfo(DictumBaseInfo dictumBaseInfo, String userName);

}
