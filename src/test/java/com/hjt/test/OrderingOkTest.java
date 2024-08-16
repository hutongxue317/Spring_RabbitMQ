package com.hjt.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.hjt.model.OrderItem;
import com.hjt.model.OrderingOk;
import com.hjt.model.RegisterOk;
import com.hjt.provider.OrderingOkProvider;
import com.hjt.provider.RegisterOkProvider;
import com.hjt.service.RabbitMQHttpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterOkTest
 * @date 2024/8/13 20:45
 * @since jdk11
 */
@SpringBootTest
public class OrderingOkTest {
    @Autowired
    private OrderingOkProvider orderingOkProvider;
    @Autowired
    private RabbitMQHttpService rabbitMQHttpService;
    @Test
    public void test() throws IOException {
        for (int i = 1; i <=5 ; i++) {
            OrderingOk orderingOk = new OrderingOk().setId(Long.valueOf(i)).setUserName("Rose")
                    .setItems(CollUtil.newArrayList(
                            new OrderItem()
                                    .setId(123)
                                    .setProductName("小米su7")
                                    .setPrice(BigDecimal.valueOf(10.89))
                                    .setProductId(108).setQty(10)));
            orderingOkProvider.send(orderingOk);
        }
      System.in.read();
    }
}
