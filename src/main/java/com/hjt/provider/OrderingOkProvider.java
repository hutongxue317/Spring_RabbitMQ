package com.hjt.provider;

import com.hjt.model.OrderingOk;
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
public class OrderingOkProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(OrderingOk msg){
        rabbitTemplate.convertAndSend("ordering_ok","",msg,message -> {
            Long id = msg.getId();
            //消息的过期时间
            int expire = 0;
            switch (id.intValue()){
                case 1:
                    expire = 1000*40;
                    break;
               case 2:
                    expire = 1000*30;
                    break;
                case 3:
                    expire = 20*1000;
                    break;
                case 4:
                    expire = 1000*10;
                    break;
            }
            //设置每个消息的过期时间
            message.getMessageProperties().setExpiration(expire+"");
            return message;
        });
    }
}
