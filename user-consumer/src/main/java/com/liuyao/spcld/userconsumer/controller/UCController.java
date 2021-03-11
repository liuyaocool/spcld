package com.liuyao.spcld.userconsumer.controller;

import com.liuyao.spcld.userapi.entity.Person;
import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import com.liuyao.spcld.userconsumer.api.UserFeignApi;
import com.liuyao.spcld.userconsumer.service.RestTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UCController {

    @Value("${server.port}")
    Integer port;
    @Value("${spring.application.name}")
    String appname;
    
    private String getAppInfo(){
        return "{" + appname + ": " + port + "}";
    }
    
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

        return getAppInfo() + userApi.alive();
    }

    @GetMapping("/aliveTimeout")
    public String aliveTimeout(){
        return getAppInfo() + userApi.aliveTimeout();
    }

    @GetMapping("/aliveRest")
    public String aliveRest(){
        return getAppInfo() + rest.alive();
    }

    @GetMapping("/aliveRestError")
    public String aliveRestError(){
        return getAppInfo() + rest.aliveError();
    }

    @GetMapping("/aliveRestTimeout")
    public String aliveRestTimeout(){
        return getAppInfo() + rest.aliveTimeout();
    }

//    @GetMapping("/aliveNoUrl")
//    public String alivenourl(){
//        return userNoUrlApi.alive();
//    }

    @GetMapping("/aliveApi")
    public String aliveApi(){
        return getAppInfo() + userApi.alive();
    }

    @GetMapping("/apiGetById")
    public String a(String id){
        return getAppInfo() + userApi.getById(id);
    }

    @GetMapping("/getMap")
    public Map<String, Object> getMap(@RequestParam Map<String, Object> map){
        Map<String, Object> res = userApi.getMap(map);
        res.put(appname + "_port", port);
        return res;
    }

    @GetMapping("/postMap")
    public Map<String, Object> postMap(@RequestParam Map<String, Object> map){
        Map<String, Object> res = userApi.postMap(map);
        res.put(appname + "_port", port);
        return res;
    }

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String, Object> map){
        Person person = new Person();
        person.setId(map.get("id").toString());
        person.setName(map.get("name").toString());
        person.addMsg(getAppInfo()+": postPerson;");
        return userApi.postPerson(person);
    }
}
