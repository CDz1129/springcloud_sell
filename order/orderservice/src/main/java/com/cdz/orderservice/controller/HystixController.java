package com.cdz.orderservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author chendezhi
 * @date 2018/4/25 14:31
 * 测试 hystrix组件
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystixController {

    /**
     * 使用 RestTemplate 实现调用服务接口
     * @return
     */

    @GetMapping("/getProductInfoList")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2000"),//设置接口过期时间
//            @HystrixProperty(name = "",value = "")
    })
    public String getProductInfoList(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8080/product", String.class);
    }

    private String fallback(){
        return "太拥挤了,请稍后再试.....";
    }

    private String defaultFallback(){
        return "默认: 太拥挤了,请稍后再试.....";
    }
}
