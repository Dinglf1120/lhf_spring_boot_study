package com.lhf.springboot.dao;

import com.lhf.springboot.entity.RemarkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: RemarkInfo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/01 16:56
 */
@Repository
public interface RemarkInfoDao extends JpaRepository<RemarkInfo, Integer> {

    @Query(value = "SELECT * FROM remark_info where IF(:keywords is null , 0 = 0, name like concat ('%', :keywords, '%')) order by create_time desc", nativeQuery= true)
    public List<RemarkInfo> findByKeywords(String keywords);


}
