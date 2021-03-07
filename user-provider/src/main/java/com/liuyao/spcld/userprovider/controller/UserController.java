package com.liuyao.spcld.userprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 写一个文档 给谁看? php
     *
     * 接口写了,只有java的客户端能用, 把
     *  php来了 再去写文档也行
     *
     *
     * 服务名
     * 接口名
     * @return
     */
    @GetMapping("/aliveOnly")
    public String alive(){
        return "ok";
    }

}
