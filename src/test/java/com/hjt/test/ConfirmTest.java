package com.hjt.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.hjt.model.OrderItem;
import com.hjt.model.OrderingOk;
import com.hjt.model.Student;
import com.hjt.provider.ConfirmProvider;
import com.hjt.provider.DelayProvider;
import com.hjt.provider.GamesProvider;
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
public class ConfirmTest {
    @Autowired
    private ConfirmProvider confirmProvider;
    @Autowired
    private GamesProvider  gamesProvider;
    @Test
    public void test1() throws IOException {
            OrderingOk orderingOk = new OrderingOk().setId(108l).setUserName("Rose")
                    .setItems(CollUtil.newArrayList(
                            new OrderItem()
                                    .setId(123)
                                    .setProductName("小米su7")
                                    .setPrice(BigDecimal.valueOf(10.89))
                                    .setProductId(108).setQty(10)));
        confirmProvider.send(orderingOk);
        System.in.read();
    }

    @Test
    public void test() throws IOException {
        RandomUtil.randomInt();
        Student student = new Student().setId(04).setName("大tao").setBrief("向210.04发送数据");
        gamesProvider.send("210.04",student);
    }
}
