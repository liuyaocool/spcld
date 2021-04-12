package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.anno.controller.BaseController;
import com.liuyao.spcld.springall.anno.controller.PersonController;
import com.liuyao.spcld.springall.anno.dao.PersonDao;
import com.liuyao.spcld.springall.anno.service.PersonService;
import com.liuyao.spcld.springall.anno.service.StudentService;
import com.liuyao.spcld.springall.anno.service.TeacherService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class IOCAnnoTest {

    // 每次new都会创建容器 new多次创建多次
    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc-anno.xml");

    @Test
    public void test01() throws SQLException {

        final PersonController pc = ctx.getBean("personController", PersonController.class);
        pc.save();
        ctx.getBean("personService", PersonService.class);
        ctx.getBean("personMapper", PersonDao.class);


    }

    @Test
    public void test02() {
        final BaseController bean = ctx.getBean(BaseController.class);
        bean.saveStudent();
        bean.saveTeacher();
        bean.saveBaseStudent();
        bean.saveBaseTeacher();
    }

    /**
     * 泛型依赖
     */
    @Test
    public void test03() {
        final TeacherService bean = ctx.getBean(TeacherService.class);
        bean.save();
        final StudentService bean1 = ctx.getBean(StudentService.class);
        bean1.save();
    }

}
