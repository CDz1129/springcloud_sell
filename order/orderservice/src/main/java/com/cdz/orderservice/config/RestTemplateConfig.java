package com.cdz.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author chendezhi
 * @date 2018/4/20 15:42
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    //使用LoadBalanced注解构造 RestTemplate
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
