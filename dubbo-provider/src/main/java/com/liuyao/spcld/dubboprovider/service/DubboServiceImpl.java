package com.liuyao.spcld.dubboprovider.service;

import com.liuyao.spcld.userapi.service.IDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0.0", timeout = 10000,
        interfaceClass = IDubboService.class)
@Service
public class DubboServiceImpl implements IDubboService{

    @Override
    public String say(String name) {
        System.out.println("hi dubbo " + name);
        return "hi dubbo " + name;
    }
}
