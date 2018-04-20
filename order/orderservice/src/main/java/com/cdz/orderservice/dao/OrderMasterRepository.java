package com.cdz.orderservice.dao;

import com.cdz.common.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chendezhi
 * @date 2018/4/20 13:32
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
}
