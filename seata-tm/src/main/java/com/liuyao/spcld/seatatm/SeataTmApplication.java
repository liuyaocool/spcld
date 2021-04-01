package com.liuyao.spcld.seatatm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SeataTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataTmApplication.class, args);
    }

    @Bean
    @LoadBalanced // 此注解必须加 否则url无法注入服务
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
