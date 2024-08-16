package com.hjt.test;

import com.hjt.model.RegisterOk;
import com.hjt.provider.RegisterOkProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RegisterOkTest
 * @date 2024/8/13 20:45
 * @since jdk11
 */
@SpringBootTest
public class RegisterOkTest {
    @Autowired
    private RegisterOkProvider registerOkProvider;

    @Test
    public void test() throws IOException {
        registerOkProvider.send(new RegisterOk().setId(1).setNickName("泥头车").setTel("123456"));
        System.in.read();
    }
}
