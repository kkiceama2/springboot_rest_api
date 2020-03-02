package com.example.restApiExample.conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(
    basePackages="com.example.restApiExample.mapper"
    ,sqlSessionFactoryRef="sqlSessionFactoryBasic"
    ,sqlSessionTemplateRef="sqlSessionTemplateBasic"
)
@EnableTransactionManagement(
    //interface를 생성 하지 않고 class를 주입해도 되도록
    proxyTargetClass = true
)
public class DBConf {
	
	//@Primary
    @Bean(name="dataSourceFota")
    public DataSource dataSourceFotaLocal() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mariadb://xxx.xxx.xxx.xxx:xxxxx/fota?autoReconnect=true&useUnicode=true&characterEncoding=utf8");
//        dataSource.setUrl("jdbc:log4jdbc:mariadb://118.220.16.112:44444/test01?autoReconnect=true&useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("xxx");
        dataSource.setPassword("xxx");
        dataSource.setMaxTotal(5);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(7200000);
        //이거 중요함 (설정 안하면 롤백 안됨)
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }
    
    @Bean(name="transactionManagerFota")
    public DataSourceTransactionManager transactionManagerFota(@Qualifier("dataSourceFota") DataSource dataSourceFota) {
        DataSourceTransactionManager txmanager = new DataSourceTransactionManager(dataSourceFota);
        txmanager.setNestedTransactionAllowed(false);
        
        return txmanager;
    }
    
    @Bean(name="mybatisConfigFota")
    public org.apache.ibatis.session.Configuration mybatisConfigFota() {
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setCacheEnabled(false);
        conf.setUseGeneratedKeys(true);
        conf.setDefaultExecutorType(ExecutorType.REUSE);
        conf.setSafeResultHandlerEnabled(false);
        conf.setMapUnderscoreToCamelCase(true);
        conf.setJdbcTypeForNull(JdbcType.NULL);
        return conf;
    }
    
    @Bean(name="sqlSessionFactoryFota")
    public SqlSessionFactory sqlSessionFactoryFota(@Qualifier("dataSourceFota") DataSource dataSourceFota,@Qualifier("mybatisConfigFota") org.apache.ibatis.session.Configuration mybatisConfigFota) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfiguration(mybatisConfigFota);
        sessionFactory.setDataSource(dataSourceFota);
        sessionFactory.setTypeAliasesPackage("com.skyautonet.fvms.fota.entity.db");
        return (SqlSessionFactory)sessionFactory.getObject();
    }
    
    @Bean(name="sqlSessionTemplateFota")
    public SqlSessionTemplate sqlSessionTemplateFota(@Qualifier("sqlSessionFactoryFota") SqlSessionFactory sqlSessionFactoryFota) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryFota);
    }
}

