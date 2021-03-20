package com.liuyao.spcld.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/page")
    public String page() {
        System.out.println("web1 page arrive");
        return "web1";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request, String msg) {
        System.out.println("/error/" + msg);
        request.setAttribute("msg", msg);
        request.getSession().setAttribute("msg", msg);
        return "error";
    }
}
