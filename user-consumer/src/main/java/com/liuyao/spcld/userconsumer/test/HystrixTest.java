package com.liuyao.spcld.userconsumer.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixTest extends HystrixCommand {

    public static void main(String[] args) {

    }

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    // 类似try
    protected Object run() throws Exception {
        return null;
    }

    @Override
    // 类似catch
    protected Object getFallback() {
        return super.getFallback();
    }
}
