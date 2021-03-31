package com.liuyao.spcld.seatatm;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SeataTmService {

    @Autowired
    RestTemplate restTemplate;

    @GlobalTransactional
    public String seataTm(String name) {

        String rm1 = restTemplate.getForObject(
                "http://seata-rm1/seata-rm1-at?name=rm1" + name, String.class);
        String rm2 = restTemplate.getForObject(
                "http://seata-rm2/seata-rm2-at?name=rm2" + name, String.class);


        return "success";
    }
}
