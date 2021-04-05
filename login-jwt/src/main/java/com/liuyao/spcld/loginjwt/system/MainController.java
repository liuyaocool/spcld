package com.liuyao.spcld.loginjwt.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller("main")
public class MainController {

    @GetMapping("list")
    public String list(HttpServletRequest req, String msg) {
        req.getSession().setAttribute("msg", msg);
        return "list";
    }

    @GetMapping("/get")
    public String get(HttpServletRequest req) {
        return req.getSession().getAttribute("msg").toString();
    }
}
