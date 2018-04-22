package com.cdz.productclient;

import com.cdz.common.domain.ProductInfo;
import com.cdz.common.urlparam.OrderFrom;
import com.cdz.common.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author CDz_
 * @create 2018-04-21 12:15
 **/
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/productIds")
    List<ProductInfo> findByProductIdList(@RequestBody List<String> productIds);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<OrderFrom.OrderItem> orderItems);
}
