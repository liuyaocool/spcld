package com.liuyao.spcld.springall.anno.dao;

import com.liuyao.spcld.springall.anno.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao extends BaseDao<Teacher> {
    @Override
    public void save() {
        System.out.println("save teacher");
    }
}
