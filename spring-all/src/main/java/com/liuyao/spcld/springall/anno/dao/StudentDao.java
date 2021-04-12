package com.liuyao.spcld.springall.anno.dao;

import com.liuyao.spcld.springall.anno.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {
    @Override
    public void save() {
        System.out.println("save student");
    }
}
