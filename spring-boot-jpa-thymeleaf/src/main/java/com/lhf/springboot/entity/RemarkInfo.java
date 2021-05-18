package com.lhf.springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: RemarkInfo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/01 16:56
 */
@Data
@Entity
@Table(name = "remark_info")
@Setter
@Getter
public class RemarkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String author;  //作者名

    @Column(length = 100)
    private String name;  //标题

    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String des;  //文章内容

    @Column(length = 1024)
    private String remark; //简介

    @Column(length = 50)
    private Date createTime;

    @Column(length = 50)
    private Date modifyTime;

}
