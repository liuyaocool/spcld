package com.liuyao.spcld.seatatm;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class SeataTmService {


    @GlobalTransactional
    public String seataTm() {



        return "success";
    }
}
