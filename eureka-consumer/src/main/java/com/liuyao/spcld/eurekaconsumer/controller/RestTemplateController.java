package com.liuyao.spcld.eurekaconsumer.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;

@RestController
public class RestTemplateController {

    @Autowired
    // 抽象 由于注册中心不确定用哪个, spring cloud抽象出了一个接口
    private DiscoveryClient client;
    @Autowired
    private EurekaClient client2;
    @Autowired
    // 负载均衡器
    private LoadBalancerClient lb;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    @Qualifier("autoRestTemplate")
    private RestTemplate autoRestTmp;

    @GetMapping("/req1")
    public String d(){

        // euk-provider: 服务主机名
        String url = "http://euk-provider/getHi";
        String reqpStr = autoRestTmp.getForObject(url, String.class);
        ResponseEntity<String> respEntity = autoRestTmp.getForEntity(url, String.class);
        System.out.println(respEntity);
        System.out.println("ok");
        return url + ": " + reqpStr;
    }

    @GetMapping("/req2")
    public String a(){

        // euk-provider: 服务主机名
        String url = "http://euk-provider/getMap";
        Map reqpStr = autoRestTmp.getForObject(url, Map.class);
        ResponseEntity<String> respEntity = autoRestTmp.getForEntity(url, String.class);
        System.out.println(respEntity);
        return url + ": " + reqpStr;
    }

    @GetMapping("/req3")
    public String b(){

        // euk-provider: 服务主机名
        String url = "http://euk-provider/getMapParam?id={1}";
        Map reqpStr = autoRestTmp.getForObject(url, Map.class, "uuidliuyao");
        url = "http://euk-provider/getMapParam?id={id}";
        Map<String, String> param = Collections.singletonMap("id", "aadfduuid");
        ResponseEntity<String> respEntity = autoRestTmp.getForEntity(url, String.class, param);
        System.out.println(respEntity);
        return url + ": " + reqpStr;
    }

    @GetMapping("/req4")
    public String c(){

        // euk-provider: 服务主机名
        String url = "http://euk-provider/postMapParam";
        Map<String, String> param = Collections.singletonMap("id", "uuidliuyao");
        Map reqpStr = autoRestTmp.postForObject(url, param, Map.class);
        return url + ": " + reqpStr;
    }

    @GetMapping("/req5")
    public String e(HttpServletResponse response) throws IOException {

        // euk-provider: 服务主机名
        String url = "http://euk-provider/postLocation";
        Map<String, String> param = Collections.singletonMap("id", "uuidliuyao");
        URI reqpStr = autoRestTmp.postForLocation(url, param, Map.class);
//        response.sendRedirect(reqpStr.toURL().toString());
        return url + ": " + reqpStr;
    }


}
