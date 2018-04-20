package com.cdz.orderservice.service;

import com.cdz.common.dto.OrderDTO; /**
 * @author chendezhi
 * @date 2018/4/20 14:54
 */
public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
}
