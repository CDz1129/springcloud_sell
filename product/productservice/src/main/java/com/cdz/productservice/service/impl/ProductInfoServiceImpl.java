package com.cdz.productservice.service.impl;

import com.cdz.common.domain.ProductInfo;
import com.cdz.common.enums.ExceptionEnum;
import com.cdz.common.enums.ProductStatusEnum;
import com.cdz.common.urlparam.OrderFrom;
import com.cdz.productservice.dao.ProductInfoRepository;
import com.cdz.productservice.exception.ProductException;
import com.cdz.productservice.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIds){
        return productInfoRepository.findByProductIdIn(productIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<OrderFrom.OrderItem> orderItems) {

        for (OrderFrom.OrderItem orderItem : orderItems) {

            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(orderItem.getProductId());
            if (!optionalProductInfo.isPresent()){
                //如果为空 抛出异常
                throw new ProductException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = optionalProductInfo.get();
            Integer productStock = productInfo.getProductStock();
            productStock = productStock - orderItem.getProductQuantity();
            if (productStock<0){
                //说明没有库存 抛出异常
                throw new ProductException(ExceptionEnum.PRODUCT_NO_STOCK);
            }
            //扣除库存
            productInfo.setProductStock(productStock);
            productInfoRepository.save(productInfo);
        }

    }
}
