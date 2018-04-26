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
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")//设置接口过期时间
////            @HystrixProperty(name = "",value = "")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//休眠时间窗口单位 毫秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//断路器打开的错误百分比条件
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
