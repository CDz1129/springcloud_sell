package com.cdz.orderservice.message;

import com.cdz.orderservice.OrderServiceApplicationTest;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chendezhi
 * @date 2018/4/23 17:43
 * 测试 发送mq
 */
@Component
public class MessageTest extends OrderServiceApplicationTest{

    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    public void testSendMQ() throws Exception {
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }
}
