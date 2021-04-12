package com.liuyao.spcld.springall.anno.controller;

import com.liuyao.spcld.springall.anno.dao.PersonDao;
import com.liuyao.spcld.springall.anno.service.PersonService;
import com.liuyao.spcld.springall.anno.service.PersonService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PersonController {

    /**
     * 默认按照 byType
     * 找到多个类型 会按照id查找 PersonService/PersonService3
     *
     */
//    @Autowired
//    private PersonService personService; // 按照类型
//    private PersonService2 personService; // 按照类型
//    private PersonService personService3;  // 按照名字

//    @Autowired
//    @Qualifier("personService") // 强制按照名字
//    private PersonService personService2;

    @Resource //jdk 默认按照名字装配，再使用类型
//    @Qualifier("personService") // 强制按照名字
    private PersonService personService3;


    public void save() {
        personService3.save();
    }

//    @Autowired // 此方法会自动调用
    public void update(PersonDao personDao) {
        System.out.println("auto update");
        personDao.update();
    }

//    @Autowired // 此方法会自动调用
    public void update1(@Qualifier("personService") PersonService personService2) {
        System.out.println("auto update1");
        personService2.save();
    }

}
