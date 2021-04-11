package com.liuyao.spcld.springall.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理 有接口限制
 * 使用第二种方式cglib 无需接口
 *
 * spring使用了 jdk、cglib两种
 */
public class MyProxy {

    public static <T> T getCalculator(final T calc) {

        Object o = Proxy.newProxyInstance(
                calc.getClass().getClassLoader(),
                calc.getClass().getInterfaces(),
                new InvocationHandler() {
                    // 注意 这里proxy为代理后的对象
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = null;
                        try {
                            System.out.println(method.getName() + "方法开始执行，参数是： " + Arrays.asList(args));
                            // 调用被代理类的对象
                            invoke = method.invoke(calc, args);
                            System.out.println(method.getName() + "方法执行结束，结果是： " + invoke);
                        } catch (Exception e) {
                            System.out.println(method.getName() + "方法执行出错： " + e.getMessage());
                        } finally {
                            System.out.println(method.getName() + "方法执行结束。");
                        }
                        return invoke;
                    }
                });

        return (T) o;

    }
}
