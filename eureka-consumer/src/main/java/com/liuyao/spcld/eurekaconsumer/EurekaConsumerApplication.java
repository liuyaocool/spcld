package com.liuyao.spcld.eurekaconsumer;

import com.liuyao.spcld.eurekaconsumer.interceptor.LoggingClientHttpRequestInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    // 单例
    @Bean
    @Primary
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    // 单例
    @Bean(value = "autoRestTemplate")
    @LoadBalanced
    public RestTemplate getAutoRestTemplate(){
        RestTemplate tmp = new RestTemplate();
        tmp.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return tmp;
    }

//    // 负载均衡策略
//    @Bean
//    public IRule myRule(){
////        return new RoundRobinRule();//默认
////        return new RetryRule();
//        return new RandomRule();
//    }


}
