package com.cdz.orderservice.service.impl;

import com.cdz.common.dto.OrderDTO;
import com.cdz.orderservice.dao.OrderDetailRepository;
import com.cdz.orderservice.dao.OrderMasterRepository;
import com.cdz.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chendezhi
 * @date 2018/4/20 14:54
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {





        return null;
    }
}
