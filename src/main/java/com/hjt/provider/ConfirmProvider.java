package com.hjt.provider;

import com.hjt.model.OrderingOk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
@Slf4j
public class ConfirmProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(OrderingOk msg){
       //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 确认消息是否被交换机接收。
             *
             * @param correlationData 包含消息相关数据的对象，用于识别消息的唯一性。
             * @param ack 表示消息是否被交换机确认接收。
             * @param cause 如果消息未被接收，提供未接收的原因。
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    String id = correlationData.getId();
                    //通过id更改订单状态
                }else {
                    log.error("{}",cause);
                }
            }
        });
        // 设置退回回调, 之后投递失败的时候才会触发
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 记录被交换机退回的消息信息。
             *
             * @param message 消息对象，包含消息体。
             * @param replyCode 返回的响应代码，用于指示退回的原因。
             * @param replyText 返回的响应文本，提供关于退回的详细信息。
             * @param exchange 退回时涉及的交换机名称。
             * @param routingKey 退回时使用的路由键。
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

                System.out.println("Message was returned: " + new String(message.getBody()));
                System.out.println("Reply code: " + replyCode);
                System.out.println("Reply text: " + replyText);
                System.out.println("Exchange: " + exchange);
                System.out.println("Routing key: " + routingKey);
            }
        });
        //CorrelationData 创建一个关联数据，用于消息的跟踪，大部分都是业务单据的id
        CorrelationData correlationData = new CorrelationData("201408145676676");
        rabbitTemplate.convertAndSend("ordering_ok","",msg,correlationData);
    }
}
