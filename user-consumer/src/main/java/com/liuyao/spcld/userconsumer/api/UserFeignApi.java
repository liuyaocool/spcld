package com.liuyao.spcld.userconsumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 不结合eureka
 *  就是自定义一个client名字
 *  用url属性指定服务器列表 url="http://ip:port/"
 * 加注解之后 拦截器会发请求(类注解+方法注解),并把结果拿回来,底层也是RestTemplate
 */
@FeignClient(name = "xxoo", url = "http://localhost:8020/")
public interface UserFeignApi {

    // 硬编码
    // 或拼接在在本类的注解中url属性后
    @GetMapping("/alive")
    public String alive();

    @GetMapping("/register")
    public String register();
}
