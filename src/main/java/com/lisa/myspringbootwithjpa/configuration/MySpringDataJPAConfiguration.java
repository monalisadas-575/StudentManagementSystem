package com.lisa.myspringbootwithjpa.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration

public class MySpringDataJPAConfiguration {

//    @Bean
//    public SimpleDriverDataSource getTheDataSource(){
//        SimpleDriverDataSource dataSource= new SimpleDriverDataSource();
//        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
//        dataSource.setUrl("jdbc:mysql://localhost:3306/your_database_name");
//        dataSource.setUsername("your_username");
//        dataSource.setPassword("your_password");
//        return dataSource;
//    }
//
//    @Bean(defaultCandidate = false)
//    @ConfigurationProperties("app.datasource")
//    public DataSource getTheDataSourceOne(){
//        return DataSourceBuilder.create().build();
//    }


}
