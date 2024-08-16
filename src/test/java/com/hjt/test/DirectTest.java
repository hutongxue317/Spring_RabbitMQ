package com.hjt.test;

import com.hjt.provider.DirectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author 胡金涛
 * @version 1.0
 * @className SpringTest
 * @date 2024/8/13 14:55
 * @since jdk11
 */
@SpringBootTest
public class DirectTest {
    @Autowired
    private DirectProvider directProvider;
    @Test
    void test() throws IOException {

        for (int i = 1; i <= 10; i++) {
            if(i%2 == 0){
                directProvider.send("rk02","hello 你好"+i);
            } else{
                directProvider.send("rk01","hello 你好"+i);
            }

        }

        System.in.read();
    }
}
