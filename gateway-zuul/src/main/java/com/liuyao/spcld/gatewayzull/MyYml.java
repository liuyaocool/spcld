package com.liuyao.spcld.gatewayzull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application-ly.yml"})
@ConfigurationProperties(prefix = "ly")
public class MyYml {

    @Value("${dizhi}")
    private String dizhi;

    public MyYml() {
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }
}
