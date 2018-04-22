package com.cdz.orderservice.service.impl;

import com.cdz.common.domain.OrderDetail;
import com.cdz.common.domain.OrderMaster;
import com.cdz.common.domain.ProductInfo;
import com.cdz.common.dto.OrderDTO;
import com.cdz.common.enums.ExceptionEnum;
import com.cdz.common.enums.OrderStatusEnum;
import com.cdz.common.enums.PayStatusEnum;
import com.cdz.common.urlparam.OrderFrom;
import com.cdz.common.utils.KeyUtil;
import com.cdz.orderservice.dao.OrderDetailRepository;
import com.cdz.orderservice.dao.OrderMasterRepository;
import com.cdz.orderservice.service.OrderService;
import com.cdz.productclient.ProductClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO createOrder(OrderDTO orderDTO) {

        List<OrderDetail> orderDetails = orderDTO.getOrderDetails();

        String orderId = KeyUtil.getUniqueKey();

        //查询购物车中所有商品
        List<String> productIds = orderDetails.stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        List<ProductInfo> byProductIdList = productClient.findByProductIdList(productIds);

        //计算总价
        BigDecimal sumprice = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDetails) {
            for (ProductInfo productInfo : byProductIdList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {

                    sumprice = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(sumprice);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    orderDetail.setOrderId(orderId);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }


        OrderMaster orderMaster = orderDTO.getOrderMaster();
        orderMaster.setOrderAmount(sumprice);
        orderMaster.setOrderId(orderId);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 后扣库存 原因等订单方保存完成后，这里如果出错，就可以回滚
        List<OrderFrom.OrderItem> orderItems = orderDetails.stream()
                .map(orderDetail -> new OrderFrom.OrderItem(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(orderItems);
        return orderDTO;
    }
}
