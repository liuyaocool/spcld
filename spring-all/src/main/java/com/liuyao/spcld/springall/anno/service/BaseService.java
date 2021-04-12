package com.liuyao.spcld.springall.anno.service;

import com.liuyao.spcld.springall.anno.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void save(){
        baseDao.save();
    }
}
