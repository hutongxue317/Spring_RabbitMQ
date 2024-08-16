package com.hjt.consumer;

import cn.hutool.core.util.StrUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡金涛
 * @version 1.0
 * @className FanoutConsumer
 * @date 2024/8/13 15:04
 * @since jdk11
 */
//@Configuration
public class FanoutConsumer {
        //创建队列
    @Bean
    public Queue queue1() {
        return QueueBuilder.durable("q01").maxLength(100).build();
    }
        //创建交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("f_ex01").durable(true).build();
    }
        //绑定
    @Bean
    public Binding binding(){
        //广播交换机会忽略RouterKey所以绑定不用填写RouterKey
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
        //触发消费者
    @RabbitListener(queues = "q01")
    public void consumer(String msg){
        System.out.println(StrUtil.format("消费者1：{}",msg));
    }
}
