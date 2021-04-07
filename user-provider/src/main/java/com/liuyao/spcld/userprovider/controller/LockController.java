package com.liuyao.spcld.userprovider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lock")
public class LockController {

    @RequestMapping("lock1")
    public String lock1() {

        return "lock1 ok";
    }

    @RequestMapping("lock2")
    public String lock2() {

        return "lock2 ok";
    }
}
