package com.hjt.test;

import com.hjt.provider.FanoutProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author 胡金涛
 * @version 1.0
 * @className FanoutTest
 * @date 2024/8/13 15:14
 * @since jdk11
 */
@SpringBootTest
public class FanoutTest {
    @Autowired
    private FanoutProvider fanoutProvider;
    @Test
    public void test() throws IOException {
        for (int i = 1; i <= 5; i++) {
            fanoutProvider.send("谢bro"+i);
        }
        //自主线程阻塞
        System.in.read();
    }
}
