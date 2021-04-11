package com.liuyao.spcld.springall.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class MyAop {

    @Pointcut("execution(* com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void myPointCut() { }

    @Pointcut("execution(* *(..))")
    public void myPointCut1() { }

    @Before("myPointCut()")
    private void before(JoinPoint jp) {
        // 获取方法签名
        Signature sign = jp.getSignature();
        System.out.println("MyAop-@Before → " + sign.getName() + "方法开始执行，参数是：" + Arrays.asList(jp.getArgs()));
    }

    @After("myPointCut()")
    public void after(JoinPoint jp) {
        System.out.println("MyAop-@After → 方法执行结束。。。over");
    }

    @AfterThrowing(throwing = "e",
            value = "execution(public Integer com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void error(JoinPoint jp, Exception e) {
        System.out.println("MyAop-@AfterThrowing → 方法执行报错：" + e.getMessage());
    }

    @AfterReturning(returning = "result",
            value = "execution(public Integer com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("MyAop-@AfterReturning → 方法执行结束，结果是：" + result);
    }

    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        final Signature sign = pjp.getSignature();
        final Object[] args = pjp.getArgs();
        Object result = null;
        try {
            System.out.println("MyAop-@Around → " + sign.getName() + "开始执行, 参数为：" + args);
            // 通过反射调用方法
            result = pjp.proceed(args);
            result = 100;
            System.out.println("MyAop-@Around → " + sign.getName() + "执行结束");
        } catch (Throwable throwable) {
            System.out.println("MyAop-@Around → " + sign.getName() + "执行异常：" + throwable.getMessage());
        } finally {

        }
        System.out.println("MyAop-@Around → " + sign.getName() + "方法返回,结果：" + result);
        return result;
    }


}
