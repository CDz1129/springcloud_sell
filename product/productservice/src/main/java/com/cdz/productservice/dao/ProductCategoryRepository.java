package com.cdz.productservice.dao;

import com.cdz.common.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chendezhi
 * @date 2018/4/20 10:00
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
