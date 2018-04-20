package com.cdz.productservice.service.impl;

import com.cdz.common.domain.ProductCategory;
import com.cdz.productservice.dao.ProductCategoryRepository;
import com.cdz.productservice.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 10:28
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }
}
