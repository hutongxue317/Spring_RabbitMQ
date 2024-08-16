package com.hjt.consumer;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.hjt.model.Student;
import com.hjt.provider.GamesProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Struct;

/**
 * @author 胡金涛
 * @version 1.0
 * @className GamesConsumer
 * @date 2024/8/16 13:41
 * @since jdk11
 */
@Configuration
@Slf4j
public class GamesConsumer {

    /*交换机*/
    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange("210.Game").build();
    }
    /*正常的接收接收*/
    @Bean
    public Queue queue1(){
        return QueueBuilder.durable("大涛是帅比").build();
    }
    /*全接受的队列*/
    @Bean
    public Queue queue2(){
        return QueueBuilder.durable("210.all").build();
    }
    /*绑定交换机*/
    @Bean
    public Binding binding1(){
        return  BindingBuilder.bind(queue1()).to(topicExchange()).with("210.04");
    }
    @Bean
    public Binding binding2(){
        return  BindingBuilder.bind(queue2()).to(topicExchange()).with("#");
    }
    @Autowired
    private  GamesProvider gamesProvider;
    //消费者
    @RabbitListener(queues = "大涛是帅比")
    public void consumer1(Student msg){
        Integer count  = 1;
        log.debug("第{}次操作",count++);
        log.debug("---------接收消息----------");
        //获得发送过来的消息
        log.debug("消息为：{}",msg);
        //睡眠两秒发送给其他人
        ThreadUtil.safeSleep(3000);
        //发送
       Integer randomInt = RandomUtil.randomInt(1, 47);
       /*路由键*/
       String roukey = "";
       if (randomInt < 10){
           roukey = "210.0"+randomInt;
        }else {
           roukey = "210."+randomInt;
       }
        log.debug("---------随机数为{}----------",randomInt);
        Student student = new Student().setId(04).setName("大涛是帅比").setBrief(StrUtil.format("{}的消息我已收到，我向{}发送消息",msg.getId(),roukey));
        log.debug("---------发送消息{}----------",student);
        gamesProvider.send(roukey,student);

    }
    @RabbitListener(queues = "210.all")
    public void consumer2(Student msg){
        log.debug("---------接收消息----------");
        //获得发送过来的消息
        log.debug("消息为：{}",msg);
    }



}
