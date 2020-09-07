/**
 * 
 */
package com.taiji.dcdb.media.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author Administrator
 *
 */

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DcdbDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "dcdbSqlSessionFactory")
public class DcdbDataSourceConfig {

    // 精确到 dcdb 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.taiji.admin.mapper";
    static final String MAPPER_LOCATION = "classpath:com/taiji/admin/mapper/*.xml";

    @Value("${dcdb.datasource.url}")
    private String url;

    @Value("${dcdb.datasource.username}")
    private String user;

    @Value("${dcdb.datasource.password}")
    private String password;

    @Value("${dcdb.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "dcdbDataSource")
    public DataSource dcdbDataSource() {
    	System.out.println("dcdb datasource created......");
    	System.out.println("dcdb datasource: "+url);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Bean(name = "dcdbTransactionManager")
//    @Primary
//    public DataSourceTransactionManager dcdbTransactionManager() {
//        return new DataSourceTransactionManager(dcdbDataSource());
//    }

    @Bean(name = "dcdbSqlSessionFactory")
    public SqlSessionFactory dcdbSqlSessionFactory(@Qualifier("dcdbDataSource") DataSource dcdbDataSource)
            throws Exception {
    	System.out.println("dcdb sessionFactory created......");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dcdbDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DcdbDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name="dcdbJdbcTemplate")
    public JdbcTemplate dcdbJdbcTemplate(
            @Qualifier("dcdbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
