package com.universal.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.universal.db.dao.ConfigDao;
import com.universal.db.dao.GroupDao;
import com.universal.db.dao.ProductDao;
import org.skife.jdbi.v2.DBI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Order(1)
@Profile("default")
public class DependencyConfiguration {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/nova_testing_db");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }


    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
        objectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapperBuilder;
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DBI getDBI() {
        return new DBI(dataSource());
    }


    @Bean
    org.springframework.boot.autoconfigure.web.ErrorAttributes defaultErrorAttribute() {
        return new ErrorAttributes();
    }

    @Bean
    public ConfigDao configDao(DBI dbi) {
        return dbi.onDemand(ConfigDao.class);
    }


    @Bean
    public ProductDao productDao() {
        return getDBI().onDemand(ProductDao.class);
    }

    @Bean
    public GroupDao groupDao() {
        return getDBI().onDemand(GroupDao.class);
    }
}
