package com.liuyao.spcld.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("page")
    public String page() {
        System.out.println("web1 page arrive");
        return "web1";
    }

    @GetMapping("loginPage")
    public String loginPage() {
        return "login";
    }
}
