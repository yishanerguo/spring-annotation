package com.wangshao.config;

import com.wangshao.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @create 2020-02-18-16:03
 */

//配置类==配置文件
@Configuration    //告诉spring这是一个配置类
public class MainConfig {

//    @Bean //给容器注册一个bean,类型为返回值的类型,id默认是方法名作为id
//    public Person person(){
//        return  new Person("lisi",20);
//    }
      //指定id
      @Bean("person") //给容器注册一个bean,类型为返回值的类型,id默认是方法名作为id
      public Person person01(){
       return  new Person("lisi",20);
      }
}
