package com.liuyao.spcld.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class UccsController {

    @Value("${server.port}")
    Integer port;
    @Value("${spring.application.name}")
    String appname;
    @Value("${myconfig}")
    String myconfig;

    @GetMapping("/gitconfig")
    public String gitconfig(){
        return myconfig;
    }

}
