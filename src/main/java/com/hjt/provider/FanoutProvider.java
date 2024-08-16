package com.hjt.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 胡金涛
 * @version 1.0
 * @className FanoutProvider
 * @date 2024/8/13 15:12
 * @since jdk11
 */
@Service
public class FanoutProvider {
    @Autowired
    //RabbitTemplate可以发送和接收消息
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        rabbitTemplate.convertAndSend("f_ex01","",msg);
    }
}
