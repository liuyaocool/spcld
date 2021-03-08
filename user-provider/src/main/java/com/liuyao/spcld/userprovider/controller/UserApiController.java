package com.liuyao.spcld.userprovider.controller;

import com.liuyao.spcld.userapi.api.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController implements UserApi {

    @Value("${server.port}")
    String port;

    @Override
    public String alive(){
        return port + " ok";
    }

}
