package com.liuyao.spcld.springall;

import com.alibaba.druid.pool.DruidDataSource;
import com.liuyao.spcld.springall.eneity.Person;
import com.liuyao.spcld.springall.proxy.MyProxy;
import com.liuyao.spcld.springall.service.Calculator;
import com.liuyao.spcld.springall.service.MyCalculator;
import com.liuyao.spcld.springall.service.PersonService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MainTest {

    @Test
    public void testCalc() {

//        final Calculator calculator = MyProxy.getCalculator(new MyCalculator());
//        System.out.println(calculator.add(12,14));

    }

    @Test
    public void testAopAnno() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop-annotation.xml");
        MyCalculator calculator = ctx.getBean(MyCalculator.class);

        calculator.div(12,0);

        System.out.println(calculator.getClass());

    }

    @Test
    public void testAop() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        MyCalculator calculator = ctx.getBean(MyCalculator.class);

        calculator.div(12,0);

        System.out.println(calculator.getClass());

    }

    @Test
    public void testJdbc() throws SQLException {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        final DruidDataSource ds = ctx.getBean("dataSource", DruidDataSource.class);
        final JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

//        String sql = "insert into tb_person(name, age) values(?,?)";
//        final int i = jdbcTemplate.update(sql, "刘耀", 16);
//        System.out.println(i);

        String sql = "select * from tb_person where id = ?";
        final List<Person> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class), 1);
        for (Person person : query) {
            System.out.println(person.toString());
        }

    }

    @Test
    public void testJdbcAnoo() throws SQLException {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        final PersonService personService = ctx.getBean(PersonService.class);
        System.out.println(personService.getPerson(1));
    }
}