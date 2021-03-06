package com.liuyao.spcld.eurekaprovider.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * 服务下线
 *  项目还能访问 但服务由于某些原因 如限流导致服务不能用
 */
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status.toString();
    }

    @Override
    public Health health() {
        if (status) {
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }
}
