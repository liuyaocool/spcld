package com.liuyao.spcld.userconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 注: 使用resttemplate+hystrix 需要在启动器上加@EnableCircuitBreaker注解
 */
@Service
public class RestTempService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://user-provider/user/alive";
        return restTemplate.getForObject(url, String.class);
    }

    @HystrixCommand(defaultFallback = "back")
    public String aliveError() {
        String url = "http://user-provider/user/aliveError";
        return restTemplate.getForObject(url, String.class);
    }

    @HystrixCommand(defaultFallback = "back")
    public String aliveTimeout() {
        String url = "http://user-provider/user/aliveTimeout";
        return restTemplate.getForObject(url, String.class);
    }

    public String back(){
        return "rest 降级";
    }

}
