package com.liuyao.spcld.eurekaconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class MainController {

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

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }

    @GetMapping("/client")
    public String client(){
        Map res = new HashMap();
        res.put("services", client.getServices());
        res.put("instances-euk-provider", client.getInstances("euk-provider"));
        return JSON.toJSONString(res);
    }

    @GetMapping("/request")
    public String client1(){
        // 集体服务
//        List<InstanceInfo> instances = client2.getInstancesById("192.168.1.6:EurekaServer:7000");
        // 使用服务名 找列表
        List<InstanceInfo> instances = client2.getInstancesByVipAddress("euk-provider", false);
        if (instances.size() > 0) {
            InstanceInfo ins = instances.get(0);
            if(ins.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + ins.getHostName() + ":" + ins.getPort() + "/getHi";
                System.out.println(url);
                RestTemplate rtmp = new RestTemplate();
                String reqpStr = rtmp.getForObject(url, String.class);
                System.out.println("resp: " + reqpStr);
            }
        }
        return "clien request";
    }

    @GetMapping("/loadBalanceReq")
    public String a(){

        // 负载均衡策略
        ServiceInstance ins = lb.choose("euk-provider");
        String url = "http://" + ins.getHost() + ":" + ins.getPort() + "/getHi";
        System.out.println("LoadBalancerClient: " + url);
        RestTemplate rtmp = new RestTemplate();
        String reqpStr = rtmp.getForObject(url, String.class);
        System.out.println("LoadBalancerClient resp: " + reqpStr);
        return "ribbonReq";
    }


    @GetMapping("/restTmpReq")
    public String b(){

        // 负载均衡策略
        ServiceInstance ins = lb.choose("euk-provider");
        String url = "http://" + ins.getHost() + ":" + ins.getPort() + "/getHi";
        String reqpStr = restTemplate.getForObject(url, String.class);
        return url + ": " + reqpStr;
    }

    @GetMapping("/discoveryReq")
    public String c(){

        List<ServiceInstance> instances = client.getInstances("euk-provider");
        // 自定义的随机算法
        ServiceInstance ins = instances.get(new Random().nextInt(instances.size()));
        //自定义轮询算法
        // ...
        // 自定义权重算法
        // ins.getMetadata();// 通过元数据设置权重
        // 自定义hash算法
        // ...
        String url = "http://" + ins.getHost() + ":" + ins.getPort() + "/getHi";
        String reqpStr = restTemplate.getForObject(url, String.class);
        return url + ": " + reqpStr;
    }

    @GetMapping("/autoUrlReq")
    public String d(){

        String url = "http://euk-provider/getHi";
        String reqpStr = autoRestTmp.getForObject(url, String.class);
        return url + ": " + reqpStr;
    }



}
