package com.hjt.test;

import com.hjt.service.RabbitMQHttpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 胡金涛
 * @version 1.0
 * @className MQMessageCount
 * @date 2024/8/16 16:17
 * @since jdk11
 */
@SpringBootTest
public class MQMessageCount {
    @Autowired
    private RabbitMQHttpService rabbitMQHttpService;
    @Test
    public void test(){
        rabbitMQHttpService.getQueueMessageCount();
    }
}
