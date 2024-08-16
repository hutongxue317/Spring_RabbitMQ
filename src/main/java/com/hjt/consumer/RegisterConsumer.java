package com.hjt.consumer;

import com.hjt.model.RegisterOk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterConsumer
 * @date 2024/8/13 19:53
 * @since jdk11
 */
//@Configuration
@Slf4j
public class RegisterConsumer {
    //1、注册队列
    /*送积分*/
    @Bean
    public Queue queue1(){
        return QueueBuilder.durable("send_point").maxLength(100).build();
    }
    /*发消息*/
    @Bean
    public Queue queue2(){
        return QueueBuilder.durable("send_message").maxLength(100).build();
    }
    //2、注交换机
    @Bean
    public FanoutExchange fanoutExchange1(){
        return ExchangeBuilder.fanoutExchange("register_ok").durable(true).build();
    }
    //3、队列交换机双绑定
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange1());
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange1());
    }
    //4、产生消费者
   // @RabbitListener(queues = "send_point")
    public void consumer(RegisterOk msg){
        log.debug("送积分->:{}",msg);
    }
    //@RabbitListener(queues = "send_message")
    public void consumer2(RegisterOk msg){
        log.debug("发短信->:{}",msg);
    }
}
