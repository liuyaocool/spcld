package com.liuyao.spcld.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @GetMapping
    public String list(HttpServletRequest req, String msg) {
        req.getSession().setAttribute("msg", msg);
        return "list";
    }

    @GetMapping("/get")
    public String get(HttpServletRequest req) {
        return req.getSession().getAttribute("msg").toString();
    }
}
