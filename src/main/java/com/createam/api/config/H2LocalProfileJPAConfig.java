package com.createam.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by lukasz@create.am on 10/08/2017.
 */
@Profile("local")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.createam.api.repository")
public class H2LocalProfileJPAConfig {

    private static final Logger log = LoggerFactory.getLogger(H2LocalProfileJPAConfig.class);

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        log.info("H2 datasource prepared!");
        return dataSource;
    }
}
