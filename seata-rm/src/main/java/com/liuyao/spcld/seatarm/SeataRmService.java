package com.liuyao.spcld.seatarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SeataRmService {

    @Autowired
    SeataRmDao seataRm1Dao;


    public String insertRm1(String name) {
        TbSeataRm tbSeataRm1 = new TbSeataRm();
        tbSeataRm1.setName(name);
        Integer integer = seataRm1Dao.insertRm1(tbSeataRm1);
        if (integer == 1) {
            return "success";
        }
        return "faild";
    }

    public String insertRm2(String name) {
        TbSeataRm tbSeataRm1 = new TbSeataRm();
        tbSeataRm1.setName(name);
        Integer integer = seataRm1Dao.insertRm2(tbSeataRm1);
        System.out.println(1/0);
        if (integer == 1) {
            return "success";
        }
        return "faild";
    }
}
