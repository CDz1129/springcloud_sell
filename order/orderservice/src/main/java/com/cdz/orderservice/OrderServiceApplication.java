package com.cdz.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.cdz.productclient")//使用feign组件调用其它客户端接口
@ComponentScan(basePackages = "com.cdz")
@EntityScan(value = "com.cdz.common.domain")//springdata jpa bean的扫描路
//@SpringBootApplication
//@EnableDiscoveryClient//申明是eureka的客户端
//@EnableCircuitBreaker
@SpringCloudApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}


