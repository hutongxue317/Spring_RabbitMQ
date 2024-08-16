package com.hjt.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 生产者
 */
@Service
public class DirectProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //生产
    public void send(String routerKey,String msg){
        rabbitTemplate.convertAndSend("d_ex01",routerKey,msg);
    }
}
