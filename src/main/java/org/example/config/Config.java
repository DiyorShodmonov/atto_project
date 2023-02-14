package org.example.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Config {

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/Atto");
//        driverManagerDataSource.setUsername("java_db_user");
//        driverManagerDataSource.setPassword("123");
//        return driverManagerDataSource;
//    }
//
//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource());
//        return jdbcTemplate;
//    }


    @Bean
    public SessionFactory getSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
}
