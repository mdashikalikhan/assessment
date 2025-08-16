package com.assessment.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.assessment.dao.mysql",
entityManagerFactoryRef = "mysqlEntityManagerFactory",
transactionManagerRef = "mySQLTransactionManager")
public class MySQLConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource mySQLDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            @Qualifier("mySQLDataSource") DataSource mysqlDataSource,
            @Qualifier("mysqlJpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(mysqlDataSource);
        factory.setPackagesToScan("com.assessment.entity.mysql");
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPersistenceUnitName("mysqlPU");
        factory.setJpaPropertyMap(props);
        return factory;
    }

    @Bean
    public JpaVendorAdapter mysqlJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager mySQLTransactionManager(@Qualifier("mysqlEntityManagerFactory")
                                                              EntityManagerFactory mySQLEntityManagerFactory) {
        return new JpaTransactionManager(mySQLEntityManagerFactory);
    }

}
