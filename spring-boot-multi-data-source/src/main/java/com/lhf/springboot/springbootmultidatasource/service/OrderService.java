package com.lhf.springboot.springbootmultidatasource.service;

import com.lhf.springboot.springbootmultidatasource.config.DataSourceEnum;
import com.lhf.springboot.springbootmultidatasource.config.DataSourceSwitcher;
import com.lhf.springboot.springbootmultidatasource.entity.Order;
import com.lhf.springboot.springbootmultidatasource.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName OrderService
 * @Desc TODO
 * @Author liuhefei
 * @Date 2021/6/24 18:41
 **/
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;


    /**
     * 读操作
     *
     * @param orderId
     * @return
     */
    @DataSourceSwitcher(DataSourceEnum.SLAVE)
    public List<Order> getOrder(String orderId) {
        return orderMapper.listOrders(orderId);

    }

    /**
     * 写操作
     *
     * @param orderId
     * @return
     */
    @DataSourceSwitcher(DataSourceEnum.MASTER)
    public List<Order> insertOrder(Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return orderMapper.saveOrder(order);
    }
}
