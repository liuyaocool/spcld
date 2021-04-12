package com.liuyao.spcld.springall.proxy;

import org.springframework.stereotype.Component;

@Component
public class MyCalculator implements Calculator {

    public Integer add(Integer i, Integer j) {
        Integer result = i + j;
//        int a = 1 /0;
        return result;
    }

    public Integer sub(Integer i, Integer j) {
        Integer result = i - j;
        return result;
    }

    public Integer mul(Integer i, Integer j) {
        Integer result = i * j;
        return result;
    }

    public Integer div(Integer i, Integer j) {
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        Integer result = i / j;
        return result;
    }
}
