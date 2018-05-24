package com.stormfives.ocpay.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.stormfives.ocpay.interceptor.SqlExeTimeInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/12/6
 * Time: 下午5:46
 *
 * mybatis 配置类,不同的sqlSessionFactory配置不同的数据源,需要在SpringBoot 启动的时候 关闭mybatis的自动配置类
 */
@Configuration
@MapperScan(basePackages = MybatisDBConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MybatisDBConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.stormfives.**.**.dao";
    static final String MAPPER_LOCATION = "classpath*:com/stormfives/**/mapper/*.xml";
    static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "dataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
//    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
//    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        SqlExeTimeInterceptor sqlExeTimeInterceptor=new SqlExeTimeInterceptor();
        Interceptor[] plugins = {sqlExeTimeInterceptor};//{};
        //sessionFactory.setPlugins(plugins);
        return sessionFactory.getObject();
    }

}
