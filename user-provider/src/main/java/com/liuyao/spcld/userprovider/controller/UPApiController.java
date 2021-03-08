package com.liuyao.spcld.userprovider.controller;

import com.liuyao.spcld.userapi.api.UserApi;
import com.liuyao.spcld.userapi.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UPApiController implements UserApi {

    @Value("${server.port}")
    Integer port;

    AtomicInteger count = new AtomicInteger();

    @Override
    public String alive(){
        int i = count.getAndIncrement();
        return port + ": 第 " + i + " 次调用";
    }

    @Override
    public String aliveTimeout() {
        try {
            switch (port){
                case 8100: Thread.sleep(1000); break;
                case 8101: Thread.sleep(4000); break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = count.getAndIncrement();
        System.out.println("-------- port:" + port + " 第 " + i + " 次调用----------");
        return port + ": 第 " + i + " 次调用";
    }

    @Override
    public String aliveError() {
        int i = count.getAndIncrement();
        System.out.println("-------- port:" + port + " 第 " + i + " 次调用----------");
        System.out.println(1/0);
        return port + ": 第 " + i + " 次调用";
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
