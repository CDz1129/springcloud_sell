package com.cdz.productservice.service;

import com.cdz.common.domain.ProductInfo;

import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 10:28
 */
public interface ProductInfoService {
    List<ProductInfo> findAllUp();
}
