package com.liuyao.spcld.userconsumer.hystrix;

import com.liuyao.spcld.userapi.entity.Person;
import com.liuyao.spcld.userconsumer.api.ConsumerApi;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderBackFactory implements FallbackFactory<ConsumerApi> {
    @Override
    public ConsumerApi create(Throwable throwable) {
        return new ConsumerApi() {
            @Override
            public Map<String, String> getMap(String id) {
                return null;
            }

            @Override
            public String alive() {
                // 可以通过具体的异常(或自定义异常) 进行不同的降级操作
                if (throwable instanceof FeignException.InternalServerError){
                    return "降级 远程服务500: " + throwable.getLocalizedMessage();
                }
                return "降级 hystrix factory: " + throwable.getLocalizedMessage();
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
        };
    }
}
