package com.liuyao.spcld.springall.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class LogUtil {

    /**
     * 执行顺序
     *  @Around {
     *      @Before
     *      try{
     *          service()
     *          @AfterReturning
     *      } catch() {
     *          @AfterThrowing
     *      }
     *      @After
     *      return
     *  }
     *
     * 通配符
     *  *
     *      匹配一个或多个
     *      M*Calculator.add(Integer, Integer)
     *      MyCalculator.*(Integer, Integer)
     *      MyCalculator.add(Integer, *)
     *      只能匹配一层路径
     *      spcld.springall.*.MyCalculator.add(Integer, Integer)
     *      spcld.*.*.MyCalculator.add(Integer, Integer)
     *      不能匹配访问修饰符，访问修饰符不确定可不写
     *      返回值可以匹配
     *  ..
     *      匹配任何参数个数
     *      MyCalculator.add(..)
     *      匹配多层路径
     *      spcld..MyCalculator.add(Integer, Integer)
     *
     * 最偷懒的方式
     *  execution(* *(..))  --匹配所有
     *  execution(* *.*(..))  --匹配所有
     *  execution(* com..*(..))
     *  *开头 可以代替所有
     *
     * 逻辑操作
     *  execution(xxx) && execution(xxx) 多个同时满足
     *  execution(xxx) || execution(xxx) 多个满足一个
     *  !execution(xxx)  取反
     *
     */

    // 抽象
    @Pointcut("execution(* com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void myPointCut() { }

    @Pointcut("execution(* *(..))")
    public void myPointCut1() { }

    /**
     * 参数严格限制
     *  JoinPoint 必须是第一个
     * @param jp
     */
    @Before("myPointCut()")
    private void before(JoinPoint jp) {
        // 获取方法签名
        Signature sign = jp.getSignature();
        System.out.println("LogUtil-@Before → " + sign.getName() + "方法开始执行，参数是：" + Arrays.asList(jp.getArgs()));
    }

    @After("myPointCut()")
    public void after(JoinPoint jp) {
        System.out.println("LogUtil-@After → 方法执行结束。。。over");
    }

    @AfterThrowing(throwing = "e",
            value = "execution(public Integer com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void error(JoinPoint jp, Exception e) {
        System.out.println("LogUtil-@AfterThrowing → 方法执行报错：" + e.getMessage());
    }

    @AfterReturning(returning = "result",
            value = "execution(public Integer com.liuyao.spcld.springall.service.MyCalculator.*(Integer, Integer))")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("LogUtil-@AfterReturning → 方法执行结束，结果是：" + result);
    }

    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        final Signature sign = pjp.getSignature();
        final Object[] args = pjp.getArgs();
        Object result = null;
        try {
            System.out.println("LogUtil-@Around → " + sign.getName() + "开始执行, 参数为：" + args);
            // 通过反射调用方法
            result = pjp.proceed(args);
            result = 100;
            System.out.println("LogUtil-@Around → " + sign.getName() + "执行结束");
        } catch (Throwable throwable) {
            System.out.println("LogUtil-@Around → " + sign.getName() + "执行异常：" + throwable.getMessage());
        } finally {

        }
        System.out.println("LogUtil-@Around → " + sign.getName() + "方法返回,结果：" + result);
        return result;
    }


}
