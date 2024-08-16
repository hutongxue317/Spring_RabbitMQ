package com.hjt.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RabbitMQConfig
 * @date 2024/8/13 20:53
 * @since jdk11
 */
@Configuration
public class RabbitMQConfig {
    @Bean //将对象转成json格式
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    /*在你的配置类中创建一个RestTemplate bean*/
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
