package com.mashibing.mult;

import com.liuyao.spcld.springbootstudy.datasource.DataSourceType;
import com.liuyao.spcld.springbootstudy.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSource remoteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds2")
//    @ConditionalOnProperty(prefix = "spring.datasource.ds2", name = "enabled", havingValue = "true")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource ds1, DataSource ds2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DS1.name(), ds1);
        targetDataSources.put(DataSourceType.DS2.name(), ds2);
        return new DynamicDataSource(ds1, targetDataSources);
    }
}
