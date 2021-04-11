package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.proxy.MyProxy;
import com.liuyao.spcld.springall.service.Calculator;
import com.liuyao.spcld.springall.service.MyCalculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
}