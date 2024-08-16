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
public class DirectConsumer {
    /*
    * 消费者
    * */
    //注册队列
    @Bean
    public Queue queue(){
        //durable创建一个持久化队列，如果传递名字就是队列名，不传递随机
        return QueueBuilder.durable("q01").maxLength(100).build();
    }
    //注册交换机
    @Bean
    public DirectExchange directExchange(){
        //durable()需要传递参数
        return ExchangeBuilder.directExchange("d_ex01").durable(true).build();
    }
    //队列和交换机进行绑定
    @Bean
    public Binding binding(){
        /**
         * 队列和交换机绑定
         * with是RouterKey
         */
        return BindingBuilder.bind(queue()).to(directExchange()).with("rk01");
    }
    //监听,消费
   /* @RabbitListener(queues = "q01")
    public void consumer(String msg){
        System.out.println("Consumer1:"+msg);
    }*/
}
