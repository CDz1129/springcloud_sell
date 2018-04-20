package com.cdz.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chendezhi
 * @date 2018/4/20 15:18
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){

        return "这是 product 的 msg";
    }
}
