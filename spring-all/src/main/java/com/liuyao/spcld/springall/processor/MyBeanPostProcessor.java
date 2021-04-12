package com.liuyao.spcld.springall.processor;

import com.liuyao.spcld.springall.eneity.Auth;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * bean增强类
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * bean初始化之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Auth) {
            // ...
        }

        System.out.println("before init: " + beanName);
        return bean;
    }


    /**
     * bean初始化之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after init: " + beanName);
        return bean;
    }
}
