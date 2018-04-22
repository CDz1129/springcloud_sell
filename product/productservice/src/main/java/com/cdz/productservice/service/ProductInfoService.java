package com.cdz.productservice.service;

import com.cdz.common.domain.ProductInfo;
import com.cdz.common.urlparam.OrderFrom;

import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 10:28
 */
public interface ProductInfoService {
    List<ProductInfo> findAllUp();
    List<ProductInfo> findByProductIdIn(List<String> productIds);

    /**
     * 扣除库存
     * @param orderItems
     */
    void decreaseStock(List<OrderFrom.OrderItem> orderItems);
}
