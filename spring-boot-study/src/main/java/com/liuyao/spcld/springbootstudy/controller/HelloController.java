package com.liuyao.spcld.springbootstudy.controller;

import com.liuyao.spcld.springbootstudy.listener.MySessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
//        request.getSession().setAttribute("a", "a");
        return "hello, 当前在线人数：" + MySessionListener.COUNT.get();
    }


    @GetMapping("select")
    @ResponseBody
    public List<Map<String, Object>> str(HttpServletRequest request) {
        String sql = "select * from tb_person";
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }


}
