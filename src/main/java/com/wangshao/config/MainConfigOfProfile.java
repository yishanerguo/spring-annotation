package com.wangshao.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wangshao.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;


/**
 * @author liutao
 * @create 2020-02-20-17:28
 * profile:
 *    spring为我们提供的可以根据当前环境,动态的激活和切换一系列组件的功能
 *
 * 开发环境,测试环境,生产环境:
 * 数据源:(/A)(/B)(/C)
 *
 * @profile: 指定组件在哪个环境的情况下才能被注册容器中
 *   1.加了环境标识的bean,只有这个环境被激活的时候才能注册到容器中,默认是default环境
 *   2.写在配置类上,只有时指定的环境时候,整个配置类里面的所有配置才能开始生效
 *   3.没有标注bean,在任何环境都是加载的
 *
 */
//@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver stringValueResolver;

    private String driverClass;

    //@Profile("test")
    @Bean
    public Yellow yellow(){
        return  new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSource(@Value("${db.passowrd}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://192.168.1.12:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://192.168.1.12:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://192.168.1.12:3306/prod");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
        driverClass = stringValueResolver.resolveStringValue("${db.driverClass}");
    }
}
