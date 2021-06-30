package com.lhf.springboot.springbootmultidatasource.mapper;

import com.lhf.springboot.springbootmultidatasource.entity.Order;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * @ClassName OrderMapper
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/24 18:42
 **/
@Mapper
public interface OrderMapper {
    List<Order> listOrders(String orderId);

    List<Order> saveOrder(Order order);
}
