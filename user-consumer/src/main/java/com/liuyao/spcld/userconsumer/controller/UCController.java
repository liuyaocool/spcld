package com.liuyao.spcld.userconsumer.controller;

import com.liuyao.spcld.userapi.entity.Person;
import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import com.liuyao.spcld.userconsumer.api.UserFeignApi;
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
