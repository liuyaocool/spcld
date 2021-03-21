package com.liuyao.spcld.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/auth1")
    @Secured({"ROLE_admid"}) // 且
    public String auth1() {
        System.out.println("web1 page arrive");
        return "web1";
    }
    @GetMapping("/auth2")
    @PreAuthorize("hasRole('ROLE_admid')") // 必须要有
    public String auth2() {
        System.out.println("web1 page arrive");
        return "web1";
    }
    @GetMapping("/auth3")
    @PreAuthorize("hasAnyRole('ROLE_admid')") // 且
    public String auth3() {
        System.out.println("web1 page arrive");
        return "web1";
    }
    @GetMapping("/auth4")
    @PreAuthorize("hasRole('ROLE_admin') AND hasRole('ROLE_user')")// 且
    public String auth4 () {
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
