package com.liuyao.spcld.loginjwt.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {


    @GetMapping("/page")
    public String index(HttpServletRequest req) {
        return "index";
    }
}
