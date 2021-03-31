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
    RestTemplate restTemplate2 = new RestTemplate();

    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://seata-rm1/seata-rm1-at?name=ucat";
        String rest1 = restTemplate.getForObject(url, String.class);
        System.out.println("rest1: " + rest1);
        try {
            String rest2 = restTemplate2.getForObject(url, String.class);
            System.out.println("rest2: " + rest2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rest1;
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
