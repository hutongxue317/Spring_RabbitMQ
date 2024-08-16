package com.hjt.consumer;

import com.hjt.model.OrderingOk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

/**
 *死信队列
 * 当消费者拒绝了队列的消息将会重试，超出重试次数不做处理就会丢失数据
 * 死信队列存放这些消息
 */
//@Configuration
@Slf4j
public class DelayConsumer {
    //1、创建队列
    @Bean
    public Queue queue(){
        /*与死信交换机关联起来*/
        return QueueBuilder
                .durable("ordering_ok_wms")
                .maxLength(10).build();
    }
    //2、创建交换机
    @Bean
    public CustomExchange delayExchange(){
        //指定消息到期后，以什么方式投递到队列
        Map<String,Object> args = Collections.singletonMap("x-delayed-type", "fanout");
        CustomExchange exchange = new CustomExchange(
        "ordering_ok_wms_exchange", "x-delayed-message", true, false,args);
        return exchange;
    }
    //3、绑定交换机
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(delayExchange()).with("").noargs();
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
