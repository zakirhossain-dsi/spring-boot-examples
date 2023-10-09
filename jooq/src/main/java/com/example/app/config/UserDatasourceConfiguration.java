package com.example.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class UserDatasourceConfiguration {

  @Primary
  @Bean(name = "userProperties")
  @ConfigurationProperties("spring.datasource.user")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "userDatasource")
  @ConfigurationProperties(prefix = "spring.datasource.user")
  public DataSource datasource(@Qualifier("userProperties") DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }

}
