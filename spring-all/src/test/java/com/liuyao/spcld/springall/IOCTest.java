package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.eneity.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    // 每次new都会创建容器 new多次创建多次
    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc.xml");
    final ClassPathXmlApplicationContext ctx2 = new ClassPathXmlApplicationContext("ioc-depends.xml");

    @Test
    public void test01() {
//        getBean("person");
//        getBean("person2");
//        getBean("person3");
//        getBean("person4");
//        getBean("person5");
//        getBean("son");
//        getBean("parent");
//        System.out.println(getBean("person6") == getBean("person6");

//        getBean("personStaticFac");
//        getBean("personInstanceFac");

//        getBean("myFactoryBean");
        getBean("person7");

//        ctx.close();
//        getBean2("person");
//        getBean2("auth", Auth.class);

    }

    private Person getBean(String name) {
        final Person person = ctx.getBean(name, Person.class);
        System.out.println(name + ": " + person);
        return person;
    }

    private Person getBean2(String name) {
        return getBean2(name, Person.class);
    }

    private <T> T getBean2(String name, Class<T> clas) {
        final T person = ctx2.getBean(name, clas);
        System.out.println(name + ": " + person);
        return person;
    }
}
