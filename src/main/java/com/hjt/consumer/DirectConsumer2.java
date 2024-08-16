package com.hjt.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡金涛
 * @version 1.0
 * @className DirectConsumer
 * @date 2024/8/13 11:16
 * @since jdk11
 */
//@Configuration
public class DirectConsumer2 {
    /*
    * 消费者
    * */
    //注册队列
    @Bean
    public Queue queue2(){
        //durable创建一个持久化队列，如果传递名字就是队列名，不传递随机
        return QueueBuilder.durable("q02").maxLength(100).build();
    }
    //注册交换机
    @Bean
    public DirectExchange directExchange2(){
        //durable()需要传递参数
        return ExchangeBuilder.directExchange("d_ex01").durable(true).build();
    }
    //队列和交换机进行绑定
    @Bean
    public Binding binding2(){
        /**
         * 队列和交换机绑定
         * with是RouterKey
         */
        return BindingBuilder.bind(queue2()).to(directExchange2()).with("rk02");
    }
    //监听
    @RabbitListener(queues = "q02")
    public void consumer(String msg){
        System.out.println("Consumer2:"+msg);
    }
}
