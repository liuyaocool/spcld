package com.liuyao.spcld.userprovider.controller;

import com.liuyao.spcld.userapi.api.UserApi;
import com.liuyao.spcld.userapi.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UPApiController implements UserApi {

    @Value("${server.port}")
    Integer port;

    @Override
    public String alive(){
        return port + ": ok";
    }

    @Override
    public String getById(String id) {
        return port + ": " + id;
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map) {
        map.put("port", port);
        map.put("method", "getMap-RequestParam");
        return map;
    }

    @Override
    public Map<String, Object> postMap(Map<String, Object> map) {
        map.put("port", port);
        map.put("method", "postMap-RequestBody");
        return map;
    }

    @Override
    public Person postPerson(Person person) {
        person.setPort(port);
        person.setMethodName("postPerson-RequestBody");
        return person;
    }

}
