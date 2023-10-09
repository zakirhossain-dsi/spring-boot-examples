package com.example.app.config;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JooqConfig {

	private final DataSource userDatasource;
	private final DataSource orderDatasource;

	public JooqConfig(@Qualifier("userDatasource") DataSource userDatasource,
					  @Qualifier("orderDatasource") DataSource orderDatasource) {
		this.userDatasource = userDatasource;
		this.orderDatasource = orderDatasource;
	}

	public Configuration getConfiguration() {

		DefaultConfiguration config = new DefaultConfiguration();

		config.set(SQLDialect.MYSQL);
		config.setDataSource(userDatasource);
		config.set(new Settings()
				.withRenderSchema(true)
				.withRenderMapping(new RenderMapping().withSchemata(
						new MappedSchema().withInput("USER").withOutput("user"),
						new MappedSchema().withInput("ORDER").withOutput("order")))
				.withRenderFormatted(true));
		return config;
	}
}
