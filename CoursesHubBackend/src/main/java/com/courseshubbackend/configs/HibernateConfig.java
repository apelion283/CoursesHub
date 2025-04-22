/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.configs;

import java.util.Properties;
import javax.sql.DataSource;
import static org.hibernate.cfg.JdbcSettings.DIALECT;
import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author Apelion283
 */
@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {

    @Autowired
    Environment env;

    //Create Session Factory Bean
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory
                = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan(new String[]{
            "com.ndd.pojos"
        });
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    //* Get Datasource config from database.properties file
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("hibernate.connection.driverClass"));
        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUsername(
                env.getProperty("hibernate.connection.username"));
        dataSource.setPassword(
                env.getProperty("hibernate.connection.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put(DIALECT, env.getProperty("hibernate.dialect"));
        props.put(SHOW_SQL, env.getProperty("hibernate.showSql"));
        return props;
    }

    /**
     * Configures a HibernateTransactionManager bean to manage transactions in a
     * Spring application using Hibernate.
     *
     * - HibernateTransactionManager enables Spring to handle transactions
     * automatically. - Allows the use of @Transactional for commit/rollback
     * handling without manual transaction management. - Injects SessionFactory
     * to manage database sessions.
     *
     * @return HibernateTransactionManager - The transaction manager for
     * Hibernate in Spring
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(
                getSessionFactory().getObject());
        return transactionManager;
    }
}
