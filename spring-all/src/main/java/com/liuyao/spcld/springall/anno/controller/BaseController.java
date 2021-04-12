package com.liuyao.spcld.springall.anno.controller;

import com.liuyao.spcld.springall.anno.dao.BaseDao;
import com.liuyao.spcld.springall.anno.dao.StudentDao;
import com.liuyao.spcld.springall.anno.dao.TeacherDao;
import com.liuyao.spcld.springall.anno.entity.Student;
import com.liuyao.spcld.springall.anno.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    BaseDao<Teacher> teacherBaseDao;

    @Autowired
    BaseDao<Student> studentBaseDao;

    public void saveBaseTeacher() {
        teacherBaseDao.save();
    }

    public void saveBaseStudent() {
        studentBaseDao.save();
    }

    public void saveTeacher() {
        teacherDao.save();
    }

    public void saveStudent() {
        studentDao.save();

    }
}
