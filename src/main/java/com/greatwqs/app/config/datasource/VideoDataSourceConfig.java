package com.greatwqs.app.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * VideoDataSourceConfig
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "com.nbp.ncloud.workbox.k8sdemo.mapper.video", sqlSessionFactoryRef = "videoSqlSessionFactory")
public class VideoDataSourceConfig {

    // 精确到 video 目录，以便跟其他数据源隔离
    private static final String PACKAGE = "com.github.greatwqs.app.mapper.video";
    private static final String MAPPER_LOCATION = "classpath:mapper/video/*.xml";

    @Value("${video.datasource.url}")
    private String url;

    @Value("${video.datasource.username}")
    private String user;

    @Value("${video.datasource.password}")
    private String password;

    @Value("${video.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "videoDataSource")
    public DataSource videoDataSource() {
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

    @Bean(name = "videoTransactionManager")
    public DataSourceTransactionManager videoTransactionManager() {
        return new DataSourceTransactionManager(videoDataSource());
    }

    @Bean(name = "videoSqlSessionFactory")
    public SqlSessionFactory videoSqlSessionFactory(@Qualifier("videoDataSource") DataSource videoDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(videoDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(VideoDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
