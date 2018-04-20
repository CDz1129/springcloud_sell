package com.cdz.productservice.service.impl;

import com.cdz.common.domain.ProductInfo;
import com.cdz.common.enums.ProductStatusEnum;
import com.cdz.productservice.dao.ProductInfoRepository;
import com.cdz.productservice.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 10:29
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfo> findAllUp() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
