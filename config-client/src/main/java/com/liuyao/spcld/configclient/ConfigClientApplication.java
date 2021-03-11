package com.liuyao.spcld.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
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
