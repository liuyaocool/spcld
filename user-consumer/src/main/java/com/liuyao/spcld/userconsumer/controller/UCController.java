package com.liuyao.spcld.userconsumer.controller;

import com.liuyao.spcld.userapi.entity.Person;
import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import com.liuyao.spcld.userconsumer.api.UserFeignApi;
import com.liuyao.spcld.userconsumer.service.RestTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UCController {

    @Autowired
    UserFeignApi userFeignApi;
//    @Autowired
//    UserFeignServerNameApi userNoUrlApi;
    @Autowired
    ConsumerApi userApi;
    @Autowired
    RestTempService rest;

    @GetMapping("/alive")
    public String alive(){

        // 请求(开) / 不请求(关) / 半请求(半开)
        // 半开 时不时试一下

        // 降级

        // 隔离 线程池限流

        // 熔断 连续失败次数达到阈值 一般网络问题

        return userApi.alive();
    }

    @GetMapping("/aliveTimeout")
    public String aliveTimeout(){
        return userApi.aliveTimeout();
    }

    @GetMapping("/aliveRest")
    public String aliveRest(){
        return rest.alive();
    }

    @GetMapping("/aliveRestError")
    public String aliveRestError(){
        return rest.aliveError();
    }

    @GetMapping("/aliveRestTimeout")
    public String aliveRestTimeout(){
        return rest.aliveTimeout();
    }

//    @GetMapping("/aliveNoUrl")
//    public String alivenourl(){
//        return userNoUrlApi.alive();
//    }

    @GetMapping("/aliveApi")
    public String aliveApi(){
        return userApi.alive();
    }

    @GetMapping("/apiGetById")
    public String a(String id){
        return userApi.getById(id);
    }

    @GetMapping("/getMap")
    public Map<String, Object> getMap(@RequestParam Map<String, Object> map){
        return userApi.getMap(map);
    }

    @GetMapping("/postMap")
    public Map<String, Object> postMap(@RequestParam Map<String, Object> map){
        return userApi.postMap(map);
    }

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String, Object> map){
        Person person = new Person();
        person.setId(map.get("id").toString());
        person.setName(map.get("name").toString());
        return userApi.postPerson(person);
    }
}
