package com.cdz.common.dto;

import com.cdz.common.domain.OrderDetail;
import com.cdz.common.domain.OrderMaster;
import lombok.Data;

import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 14:55
 */
@Data
public class OrderDTO {

    private List<OrderDetail> orderDetails;

    private OrderMaster orderMaster;
}
