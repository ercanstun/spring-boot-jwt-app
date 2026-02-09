package com.bezkoder.springjwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.bezkoder.springjwt.erp.repository",
        entityManagerFactoryRef = "erpEntityManagerFactory",
        transactionManagerRef = "erpTransactionManager"
)
public class ErpDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.erp")
    public DataSource erpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean erpEntityManagerFactory(
            @Qualifier("erpDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.bezkoder.springjwt.erp.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }

    @Bean
    public JpaTransactionManager erpTransactionManager(
            @Qualifier("erpEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }


    @Bean
    public JdbcTemplate erpJdbcTemplate(@Qualifier("erpDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate erpNamedParameterJdbcTemplate(@Qualifier("erpDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
