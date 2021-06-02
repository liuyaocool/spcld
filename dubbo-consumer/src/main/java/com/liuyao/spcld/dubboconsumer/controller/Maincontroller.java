package com.liuyao.spcld.dubboconsumer.controller;

import com.liuyao.spcld.userapi.service.IDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class Maincontroller {

    // url 参数 绕过注册中心 直连服务提供者
    @DubboReference(version = "1.0.0")
    IDubboService iDubboService;

    @RequestMapping("/say")
    public String say() {

        return iDubboService.say("liuyao");
    }
}
