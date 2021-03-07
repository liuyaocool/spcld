package com.liuyao.spcld.userconsumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务名方式 eureka
 *
 * 与RestTemplate没本质区别
 * 优点
 *  没有代码侵入
 *  方便做异构系统
 */
//@FeignClient(name = "user-provider")
public interface UserFeignServerNameApi {

    // 或拼接在在本类的注解中url属性后
    @GetMapping("/alive")
    public String alive();

    @GetMapping("/register")
    public String register();
}
