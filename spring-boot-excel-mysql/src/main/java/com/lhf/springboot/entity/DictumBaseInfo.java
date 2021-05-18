package com.lhf.springboot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName: DictumBaseInfo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/5/29 10:45
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class DictumBaseInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String content;

    private Integer showed;

    private Date createTime;

    private String createUser;
}
