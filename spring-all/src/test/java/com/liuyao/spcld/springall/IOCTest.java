package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.eneity.Auth;
import com.liuyao.spcld.springall.eneity.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    @Test
    public void test01() {
//        getBean("person");
//        getBean("person2");
//        getBean("person3");
//        getBean("person4");
//        getBean("person5");
//        getBean("son");
//        getBean("parent");

        getBean2("person");
        getBean2("auth", Auth.class);
    }

    private void getBean(String name) {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc.xml");
        final Person person = ctx.getBean(name, Person.class);
        System.out.println(name + ": " + person);
    }

    private void getBean2(String name) {
        getBean2(name, Person.class);
    }
    private <T> void getBean2(String name, Class<T> clas) {
        final ClassPathXmlApplicationContext ctx2 = new ClassPathXmlApplicationContext("ioc-depends.xml");
        final T person = ctx2.getBean(name, clas);
        System.out.println(name + ": " + person);
    }
}
