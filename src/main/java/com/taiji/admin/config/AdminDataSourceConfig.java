/**
 * 
 */
package com.taiji.admin.config;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * sw-view com.taiji.admin.config AdminDataSourceConfig.java
 *
 * @author hsl
 *
 * 2019年11月24日 上午10:35:24
 *
 * desc:
 */

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = AdminDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "adminSqlSessionFactory")
public class AdminDataSourceConfig {

    // 精确到 admin 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.taiji.admin.mapper";
    static final String MAPPER_LOCATION = "classpath:com/taiji/admin/mapper/*.xml";

    @Value("${admin.datasource.url}")
    private String url;

    @Value("${admin.datasource.username}")
    private String user;

    @Value("${admin.datasource.password}")
    private String password;

    @Value("${admin.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "adminDataSource")
    @Primary
    public DataSource adminDataSource() {
    	System.out.println("admin datasource created......");
    	System.out.println("admin datasource: "+url);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "adminTransactionManager")
    @Primary
    public DataSourceTransactionManager adminTransactionManager() {
        return new DataSourceTransactionManager(adminDataSource());
    }

    @Bean(name = "adminSqlSessionFactory")
    @Primary
    public SqlSessionFactory adminSqlSessionFactory(@Qualifier("adminDataSource") DataSource adminDataSource)
            throws Exception {
    	System.out.println("admin sessionFactory created......");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(adminDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(AdminDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate(
            @Qualifier("adminDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
