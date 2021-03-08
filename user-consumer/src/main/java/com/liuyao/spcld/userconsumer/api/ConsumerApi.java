package com.liuyao.spcld.userconsumer.api;

import com.liuyao.spcld.userapi.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 会从eureka中做负载
 */
@FeignClient(name = "user-provider")
public interface ConsumerApi extends UserApi {

}
