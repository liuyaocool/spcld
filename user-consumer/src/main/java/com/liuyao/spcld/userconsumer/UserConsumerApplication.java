package com.liuyao.spcld.userconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker // 启动Hystrix的注解
@EnableFeignClients //内置Hystrix的注解 所以feign整合hystrix不需要加上一个注解
public class UserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

    // 单例
    @Bean
    @LoadBalanced
    public RestTemplate getAutoRestTemplate(){
        RestTemplate tmp = new RestTemplate();
//        tmp.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return tmp;
    }

}
