package com.liuyao.spcld.seatatm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SeataTmController {


    @PostMapping("/seataTm")
    public String seataTm() {


        return "success";
    }
}
