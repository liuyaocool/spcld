package com.liuyao.spcld.userconsumer.hystrix;

import com.liuyao.spcld.userapi.entity.Person;
import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 注意: 实现接口不能有RequestMapping,会注册两次
 */
@Component
public class UserProviderBack implements ConsumerApi {

    @Override
    public Map<String, String> getMap(String id) {
        return null;
    }

    @Override
    public String alive() {
        return "降级了";
    }

    @Override
    public String aliveTimeout() {
        return null;
    }

    @Override
    public String aliveError() {
        return null;
    }

    @Override
    public String getById(String id) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
