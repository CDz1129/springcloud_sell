package com.cdz.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author chendezhi
 * @date 2018/4/20 15:22
 */
@RestController
@Slf4j
public class ClientController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //第一种方式(直接使用restTemplate url写死 )
//        RestTemplate restTemplate = new RestTemplate();
//        String msg = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//        log.info("这个是是product /msg接口信息:::{}",msg);


//        第二种方式 (使用loadBalancerClient 选择对应的服务),通过应用名 动态获取 IP 和 端口
        ServiceInstance product = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", product.getHost(), product.getPort())+"/msg";
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(url, String.class);
        log.info("这个是是product /msg接口信息:::{}",msg);

        //第三种方式(利用@LoadBalanced 可在restTemplate直接使用应用的名字)
//        String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("这个是是product /msg接口信息:::{}",msg);
        return msg;
    }

}
