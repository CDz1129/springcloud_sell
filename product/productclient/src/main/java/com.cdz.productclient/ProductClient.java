package com.cdz.productclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CDz_
 * @create 2018-04-21 12:15
 **/
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("msg")
    String getProductMsg();
}
