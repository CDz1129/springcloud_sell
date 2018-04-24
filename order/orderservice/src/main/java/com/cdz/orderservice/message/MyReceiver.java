package com.cdz.orderservice.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chendezhi
 * @date 2018/4/23 17:22
 * 接收mq消息
 */
@Slf4j
@Component
public class MyReceiver {


    @RabbitListener(queuesToDeclare = @Queue("myQueue"))//自动创建队列
    public void process(String message){
        log.info("MyReceiver:{}",message);
    }
}
