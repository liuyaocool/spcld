package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.eneity.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc.xml");

    @Test
    public void test01() {
        final Person person = ctx.getBean("person", Person.class);
        final Person person2 = ctx.getBean("person2", Person.class);
        final Person person3 = ctx.getBean("person3", Person.class);
        final Person person4 = ctx.getBean("person4", Person.class);
        System.out.println(person);
        System.out.println(person2);
        System.out.println(person3);
        System.out.println(person4);

        getBean("person5");
    }

    private void getBean(String name) {
        final Person person = ctx.getBean(name, Person.class);
        System.out.println(person);
    }
}
