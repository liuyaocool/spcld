package com.liuyao.spcld.userconsumer.controller;

import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import com.liuyao.spcld.userconsumer.api.UserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    UserFeignApi userFeignApi;
//    @Autowired
//    UserFeignServerNameApi userNoUrlApi;
    @Autowired
    ConsumerApi userApi;

    @GetMapping("/alive")
    public String alive(){
        return userFeignApi.alive();
    }

//    @GetMapping("/aliveNoUrl")
//    public String alivenourl(){
//        return userNoUrlApi.alive();
//    }

    @GetMapping("/aliveApi")
    public String aliveApi(){
        return userApi.alive();
    }
}
