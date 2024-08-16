package com.hjt.provider;

import com.hjt.model.RegisterOk;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterOkProvider
 * @date 2024/8/13 19:53
 * @since jdk11
 */
@Service
public class RegisterOkProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(RegisterOk registerOk){
       rabbitTemplate.convertAndSend("register_ok","",registerOk);
    }
}
