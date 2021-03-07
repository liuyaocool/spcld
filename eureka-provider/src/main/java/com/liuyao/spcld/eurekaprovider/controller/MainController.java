package com.liuyao.spcld.eurekaprovider.controller;

import com.liuyao.spcld.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi! 我的port: " + port;
    }

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
