package com.liuyao.spcld.seatarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SeataRmController {

    @Autowired
    SeataRmService seataRm1Service;

    @GetMapping("/seata-rm1-at")
    public String seataTm(String name) {
        return seataRm1Service.insertRm1(name);
    }

    @GetMapping("/seata-rm2-at")
    public String seataTm1(String name) {
        return seataRm1Service.insertRm2(name);
    }

}
