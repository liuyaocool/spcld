package com.liuyao.spcld.springall.anno.service;

import com.liuyao.spcld.springall.anno.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService2 {

    @Autowired
    private PersonDao personDao;

    public void save() {
        System.out.println(this.getClass().getSimpleName());
        personDao.save();
    }
}
