package com.lhf.springboot.service;

import com.lhf.springboot.common.Pager;
import com.lhf.springboot.dao.RemarkInfoDao;
import com.lhf.springboot.entity.RemarkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RemarkInfoServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/2 10:15
 */
@Service
public class RemarkInfoServiceImpl implements RemarkInfoService {

    @Autowired
    private RemarkInfoDao remarkInfoDao;

    @Override
    public Pager<RemarkInfo> findAll(Map<String, String> map, int size) throws Exception {
        List<RemarkInfo> list = remarkInfoDao.findByKeywords(map.get("keywords"));
        Pager<RemarkInfo> pager = new Pager<>(list, 1, size);
        if(map.get("page") != null){
            pager = new Pager<RemarkInfo>(list, Integer.parseInt(map.get("page") + ""), size);
        }
        return pager;
    }

    @Override
    public void saveRemarkInfo(Map<String, String> map) throws Exception {
        RemarkInfo remarkInfo = new RemarkInfo();
        if(map.get("id") != null && !map.get("id").equals("")){
            remarkInfo.setId(Integer.parseInt(map.get("id")));
            map.put("msg", "修改");
        }
        map.put("msg", "添加");
        remarkInfo.setAuthor(map.get("author"));
        remarkInfo.setName(map.get("name"));
        remarkInfo.setRemark(map.get("remark"));
        remarkInfo.setDes(map.get("des"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(map.get("createTime") == null || "".equals(map.get("createTime"))){
            remarkInfo.setCreateTime(new Date());
        }else {
            remarkInfo.setCreateTime(sdf.parse(map.get("createTime")));
        }

        if(map.get("modifyTime") == null || "".equals(map.get("modifyTime"))){
            remarkInfo.setModifyTime(new Date());
        }else {
            remarkInfo.setModifyTime(sdf.parse(map.get("modifyTime")));
        }



        remarkInfoDao.save(remarkInfo);
    }

    @Override
    public void deteleRemarkInfoById(int id) throws Exception {
        remarkInfoDao.deleteById(id);

    }
}
