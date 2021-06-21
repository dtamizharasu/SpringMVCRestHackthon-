package com.springmvc.rest.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.springmvc.rest")
public class AppConfig {

    public Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.hbm2ddl.auto", "update"); // create - will create tables
        return prop;

    }

    @Bean
    @Autowired
    public DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/estore?createDatabaseIfNotExist=true");
        bds.setUsername("root");
        bds.setPassword("root");
        return bds;
    }

    @Bean
    @Autowired
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(dataSource);
        lsfb.addProperties(getHibernateProperties());
        lsfb.scanPackages("com.springmvc.rest");
        return lsfb.buildSessionFactory();
    }


    @Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager htm = new HibernateTransactionManager(sessionFactory);
        return htm;
    }
}
