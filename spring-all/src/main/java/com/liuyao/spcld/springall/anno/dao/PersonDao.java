package com.liuyao.spcld.springall.anno.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository(value = "personMapper")
//@Scope(value = "prototype") // 多例
public class PersonDao {
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " save success");
    }

    public void update() {
        System.out.println(this.getClass().getSimpleName() + " update success");
    }
}
