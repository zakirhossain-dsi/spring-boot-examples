package com.example.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class OrderDatasourceConfiguration {

  @Bean(name = "orderProperties")
  @ConfigurationProperties("spring.datasource.order")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean(name = "orderDatasource")
  @ConfigurationProperties(prefix = "spring.datasource.order")
  public DataSource datasource(@Qualifier("orderProperties") DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }
}
