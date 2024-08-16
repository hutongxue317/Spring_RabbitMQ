package com.hjt.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author 胡金涛
 * @version 1.0
 * @className RabbitMQHttpService
 * @date 2024/8/15 21:46
 * @since jdk11
 */
@Service
public class RabbitMQHttpService {

    private static final String RABBITMQ_HOST = "192.168.29.131";
    private static final String RABBITMQ_PORT = "15672";
    private static final String RABBITMQ_USER = "guest";
    private static final String RABBITMQ_PASS = "guest";
    private static final String QUEUE_NAME = "canal_q01";

    private final RestTemplate restTemplate;

    @Autowired
    public RabbitMQHttpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getQueueMessageCount() {
       /* //new一个浏览器请求头
        HttpHeaders headers = new HttpHeaders();
        *//*把账号和密码携带过去*//*
        headers.setBasicAuth(RABBITMQ_USER, RABBITMQ_PASS);
        *//*给出请求头的格式否则会报错*//*
        headers.set("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=");*/

        HttpResponse authorization  = HttpRequest.get("http://192.168.21.100:15672/api/queues/\\%2f/canal_q01").header("Authorization","Basic Z3Vlc3Q6Z3Vlc3Q=").execute();
        if (authorization.getStatus() == 200){
            String body = authorization.body();
            System.out.println(body);
            JSONArray jsonArray = JSONUtil.parseArray(body);
            String mm = "";
        }
        String mm = "";
    }
}
