package com.hjt.provider;

import com.hjt.model.OrderingOk;
import com.hjt.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 胡金涛
 * @version 1.0
 * @className OrderingOkProvider
 * @date 2024/8/14 11:27
 * @since jdk11
 */
@Service
@Slf4j
public class GamesProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String RouterKey,Student student){
        rabbitTemplate.convertAndSend("210.Game",RouterKey,student);
    }
}
