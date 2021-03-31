package com.liuyao.spcld.seatatm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SeataTmController {

    @Autowired
    SeataTmService seataTmService;

    @GetMapping("/seataTm")
    public String seataTm(String name) {
        String s = seataTmService.seataTm(name);
        return s;
    }
}
