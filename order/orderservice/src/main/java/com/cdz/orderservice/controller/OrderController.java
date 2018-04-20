package com.cdz.orderservice.controller;

import com.cdz.common.domain.OrderDetail;
import com.cdz.common.domain.OrderMaster;
import com.cdz.common.dto.OrderDTO;
import com.cdz.common.urlparam.OrderFrom;
import com.cdz.common.utils.ReslutUtil;
import com.cdz.common.vo.ResultVo;
import com.cdz.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chendezhi
 * @date 2018/4/20 13:37
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    private static OrderDetail orderItem2OrderDetail(OrderFrom.OrderItem item) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(item, orderDetail);
        return orderDetail;
    }

    public ResultVo create(@Validated OrderFrom orderFrom, BindingResult result) {

        //参数校验
        if (result.hasErrors()){
            return ReslutUtil.error(result.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderFrom2OrderDTO(orderFrom);

        OrderDTO resultOrderDto = orderService.createOrder(orderDTO);



        return null;
    }

    private OrderDTO orderFrom2OrderDTO(OrderFrom orderFrom) {
        OrderDTO orderDTO = new OrderDTO();

        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setBuyerAddress(orderFrom.getAddress());
        orderMaster.setBuyerName(orderFrom.getName());
        orderMaster.setBuyerPhone(orderFrom.getPhone());

        List<OrderFrom.OrderItem> items = orderFrom.getItems();

        List<OrderDetail> orderDetails = items.stream()
                .map(OrderController::orderItem2OrderDetail)
                .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }

}
