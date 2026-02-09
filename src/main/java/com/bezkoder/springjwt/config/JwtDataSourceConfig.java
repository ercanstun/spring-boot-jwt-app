package com.bezkoder.springjwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.bezkoder.springjwt.jwt.repository",
                "com.bezkoder.springjwt.metric.repository"
        },
        entityManagerFactoryRef = "jwtEntityManagerFactory",
        transactionManagerRef = "jwtTransactionManager"
)
public class JwtDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.jwt")
    public DataSource jwtDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean jwtEntityManagerFactory(
            @Qualifier("jwtDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(
                "com.bezkoder.springjwt.jwt.model",
                "com.bezkoder.springjwt.metric.entity"
        );
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }


    @Bean
    @Primary
    public JpaTransactionManager jwtTransactionManager(
            @Qualifier("jwtEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }



}
