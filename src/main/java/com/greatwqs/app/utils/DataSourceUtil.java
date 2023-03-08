package com.greatwqs.app.utils;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 *
 * @author wang.qingsong
 * Create on 2019/9/23
 */
public class DataSourceUtil {

	/****
	 * get DataSource for com.alibaba.druid.pool.DruidDataSource
	 * @param url
	 * @param username
	 * @param password
	 * @param driverClassName
	 * @param conInitialSize
	 * @param conMaxActive
	 * @return
	 */
	public static final DataSource getDruid(final String url, final String username,
		final String password, final String driverClassName, final int conInitialSize, final int conMaxActive) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setInitialSize(conInitialSize);
		dataSource.setMaxActive(conMaxActive);
		dataSource.setValidationQuery("select 1");
		// test connection
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnReturn(false);
		return dataSource;
	}
}
