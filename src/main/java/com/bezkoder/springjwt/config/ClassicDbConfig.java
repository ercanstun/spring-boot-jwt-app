package com.bezkoder.springjwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ClassicDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.classic")
    public DataSource classicDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "classicJdbcTemplate")
    public JdbcTemplate classicJdbcTemplate(
            @Qualifier("classicDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
