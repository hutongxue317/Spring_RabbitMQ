package com.hjt.test;

import cn.hutool.core.collection.CollUtil;
import com.hjt.model.OrderItem;
import com.hjt.model.OrderingOk;
import com.hjt.provider.DelayProvider;
import com.hjt.provider.OrderingOkProvider;
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
public class DelayTest {
    @Autowired
    private DelayProvider delayProvider;

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
            delayProvider.send(orderingOk);
        }
      System.in.read();
    }
}
