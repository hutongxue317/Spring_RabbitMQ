package com.hjt.consumer;

import com.hjt.model.OrderingOk;
import com.hjt.model.RegisterOk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *死信队列
 * 当消费者拒绝了队列的消息将会重试，超出重试次数不做处理就会丢失数据
 * 死信队列存放这些消息
 */
//@Configuration
@Slf4j
public class OrderingOkConsumer {
    //1、创建死信队列
    @Bean
    public Queue deadQueue(){
        return QueueBuilder.durable("dead_ordering_ok_wms").build();
    }
    //2、死信交换机
    @Bean
    public DirectExchange deadExchange(){
        return ExchangeBuilder.directExchange("dead_ex").durable(true).build();
    }
    //3、绑定交换机
    @Bean
    public Binding bindingDead(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead_ordering_ok_wms");
    }
    //1、创建队列
    @Bean
    public Queue queue(){
        /*与死信交换机关联起来*/
        return QueueBuilder
                .durable("ordering_ok_wms")
                /*链接到死信交换机*/
                .deadLetterExchange("dead_ex")
                /*RouterKey*/
                .deadLetterRoutingKey("dead_ordering_ok_wms")
                /*设置队列过期时间，时间到达后将所有的消息送入死信*/
                //.ttl(20*1000)
                .maxLength(10).build();
    }
    //2、创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("ordering_ok").durable(true).build();
    }
    //3、绑定交换机
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }
    //4、创建消费者
    //@RabbitListener(queues = "ordering_ok_wms")
    public void consumer(OrderingOk msg){
        log.debug("订单为：{}",msg);
        //手动抛出异常，拒绝消息后超过重试次数会数据丢包
        //引入死信队列防止丢包
        //int i = 5 / 0 ;
    }
}
