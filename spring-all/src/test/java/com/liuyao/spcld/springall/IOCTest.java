package com.liuyao.spcld.springall;

import com.alibaba.druid.pool.DruidDataSource;
import com.liuyao.spcld.springall.eneity.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class IOCTest {

    // 每次new都会创建容器 new多次创建多次
    final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc.xml");
    final ClassPathXmlApplicationContext ctx2 = new ClassPathXmlApplicationContext("ioc-depends.xml");

    @Test
    public void test01() throws SQLException {
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
//        getBean("person7");

//        final DruidDataSource dataSource = getBean("dataSource", DruidDataSource.class);
//        System.out.println(dataSource.getConnection());

//        final DruidDataSource dataSourceProperties = getBean("dataSourceProperties", DruidDataSource.class);
//        System.out.println(dataSourceProperties.getConnection());

//        getBean("person8");
        getBean("person9");

//        ctx.close();
//        getBean2("person");
//        getBean2("auth", Auth.class);

    }

    private Person getBean(String name) {
        return getBean(ctx, name, Person.class);
    }

    private <T> T getBean(String name, Class<T> tClass) {
        return getBean(ctx, name, tClass);
    }

    private Person getBean2(String name) {
        return getBean(ctx2, name, Person.class);
    }

    private <T> T getBean(ClassPathXmlApplicationContext appCtx, String name, Class<T> clas) {
        final T person = appCtx.getBean(name, clas);
        System.out.println(name + ": " + person);
        return person;
    }
}
