package com.liuyao.spcld.userapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/user")
public interface UserApi {

    /**
     * 可以写注释
     * @return
     */
    @GetMapping("/alive")
    public String alive();
}
