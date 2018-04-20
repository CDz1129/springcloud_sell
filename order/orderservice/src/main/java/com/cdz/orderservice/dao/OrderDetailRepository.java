package com.cdz.orderservice.dao;

import com.cdz.common.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chendezhi
 * @date 2018/4/20 13:33
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
}
