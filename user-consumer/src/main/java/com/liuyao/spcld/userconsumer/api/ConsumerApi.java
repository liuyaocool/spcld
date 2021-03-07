package com.liuyao.spcld.userconsumer.api;

import com.liuyao.spcld.userapi.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-provider")
public interface ConsumerApi extends UserApi {

}
