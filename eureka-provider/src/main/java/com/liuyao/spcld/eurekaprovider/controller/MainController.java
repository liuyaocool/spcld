package com.liuyao.spcld.eurekaprovider.controller;

import com.liuyao.spcld.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
