package com.liuyao.spcld.eurekaprovider.controller;

import com.liuyao.spcld.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi! 我的port: " + port;
    }

    @GetMapping("/getMap")
    public Map<String, String> getMap(){
        return Collections.singletonMap("id", "100");
    }

    @GetMapping("/getMapParam")
    public Map<String, String> getMap(String id){
        return Collections.singletonMap("id", id);
    }

    @PostMapping("/postMapParam")
    public Map<String, String> postMap(@RequestBody Map param){
        return Collections.singletonMap("id", param.get("id").toString());
    }

    @PostMapping("/postLocation")
    public URI postLocation(@RequestBody Map param,
                            HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd=" + param.get("id"));
        response.addHeader("Location", uri.toString()); // 不加 client取不出uri的值
        return uri;
    }

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }
}
