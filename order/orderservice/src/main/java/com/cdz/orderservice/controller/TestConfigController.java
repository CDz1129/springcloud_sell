package com.cdz.orderservice.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CDz_
 * @create 2018-04-22 10:48
 **/
@RestController
@RefreshScope
public class TestConfigController {

    @Value("${ebv}")
    private String profiles;

    @GetMapping("ebv")
    public String getEbv(){
        return profiles;
    }
}
