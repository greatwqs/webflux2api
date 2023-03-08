package com.greatwqs.app.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * UserDataSourceConfig
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "com.nbp.ncloud.workbox.k8sdemo.mapper.user", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    private static final String MAPPER_LOCATION = "classpath:mapper/user/*.xml";

    @Value("${user.datasource.url}")
    private String url;

    @Value("${user.datasource.username}")
    private String user;

    @Value("${user.datasource.password}")
    private String password;

    @Value("${user.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "userDataSource")
    @Primary
    public DataSource userDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        // add for init param
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(2);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        return dataSource;
    }

    @Bean(name = "userTransactionManager")
    @Primary
    public DataSourceTransactionManager userTransactionManager() {
        return new DataSourceTransactionManager(userDataSource());
    }

    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(UserDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
