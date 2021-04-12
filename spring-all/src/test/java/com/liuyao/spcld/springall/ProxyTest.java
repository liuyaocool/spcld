package com.liuyao.spcld.springall;

import com.liuyao.spcld.springall.proxy.Calculator;
import com.liuyao.spcld.springall.proxy.MyCalculator;
import com.liuyao.spcld.springall.proxy.MyProxy;
import org.junit.Test;

public class ProxyTest {


    @Test
    public void testCalc() {

        final Calculator calculator = MyProxy.getCalculator(new MyCalculator());
        System.out.println(calculator.add(12,14));

    }

    @Test
    public void test01() {

    }
}
