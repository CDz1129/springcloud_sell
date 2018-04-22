package com.cdz.orderservice.controller;

import com.cdz.common.domain.OrderDetail;
import com.cdz.common.domain.OrderMaster;
import com.cdz.common.dto.OrderDTO;
import com.cdz.common.urlparam.OrderFrom;
import com.cdz.common.utils.ResultUtil;
import com.cdz.common.vo.ResultVo;
import com.cdz.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chendezhi
 * @date 2018/4/20 13:37
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    private static OrderDetail orderItem2OrderDetail(OrderFrom.OrderItem item) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(item, orderDetail);
        return orderDetail;
    }

    @PostMapping("/create")
    public ResultVo create(@RequestBody @Validated OrderFrom orderFrom, BindingResult result) {

        //参数校验
        if (result.hasErrors()) {
            return ResultUtil.error(result.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderFrom2OrderDTO(orderFrom);
        OrderDTO resultOrderDto = orderService.createOrder(orderDTO);

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("orderId",resultOrderDto.getOrderMaster().getOrderId());
        return ResultUtil.success(resultMap);
    }

    private OrderDTO orderFrom2OrderDTO(OrderFrom orderFrom) {
        OrderDTO orderDTO = new OrderDTO();

        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setBuyerAddress(orderFrom.getAddress());
        orderMaster.setBuyerName(orderFrom.getName());
        orderMaster.setBuyerPhone(orderFrom.getPhone());
        orderMaster.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderFrom.OrderItem> items = orderFrom.getItems();

        List<OrderDetail> orderDetails = items.stream()
                .map(OrderController::orderItem2OrderDetail)
                .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetails);
        orderDTO.setOrderMaster(orderMaster);
        return orderDTO;
    }


}
