package com.liuyao.spcld.userconsumer.api;

import com.liuyao.spcld.userapi.api.UserApi;
import com.liuyao.spcld.userconsumer.hystrix.UserProviderBack;
import com.liuyao.spcld.userconsumer.hystrix.UserProviderBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 会从eureka中做负载
 * 继承的接口中的@RequestMapping("/user")会由feign自动拼接
 * fallback 配置的类回到spring 容器中找
 */
//@FeignClient(name = "user-provider", fallback = UserProviderBack.class)
@FeignClient(name = "user-provider", fallbackFactory = UserProviderBackFactory.class)
public interface ConsumerApi extends UserApi {

    /**
     * 坑
     * 这里的GetMapping是给Feign看的 get请求:user-provider/getMap?id={}
     *  同理@RequestParam("id") 也是给feign看的 没有此注解feign读不出来
     *  继承的接口同理
     * @param id
     * @return
     */
    @GetMapping("/consumerMap")
    public Map<String, String> getMap(@RequestParam("id") String id);
}
