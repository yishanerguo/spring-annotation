package com.wangshao.config;

import com.wangshao.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liutao
 * @create 2020-02-20-0:23
 */
//使用@propertySource读取外部配置文件中的k/v保存到运行的环境变量中,加载完外部配置文件以后使用${}去除配置文件的值
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person(){
        return new Person();
    }
}
