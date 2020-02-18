package com.wangshao.config;

import com.wangshao.bean.Person;
import com.wangshao.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.naming.ldap.Control;

/**
 * @author liutao
 * @create 2020-02-18-16:03
 */

//配置类==配置文件
@Configuration    //告诉spring这是一个配置类
/*@ComponentScan(value = "com.wangshao",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})}
        , useDefaultFilters = false) //包扫描*/
/*
@ComponentScan(value = "com.wangshao",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})}) //包扫描
*/
//excludeFilters = Filter[] :指定扫描的时候按照什么规则排除哪些组件
//includeFilters = Filter[] :指定扫描的守只需要包含哪些组件
//另外一种形式
@ComponentScans(
        value = {
              @ComponentScan(value = "com.wangshao",includeFilters = {
      /*  @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),*/
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})}
        , useDefaultFilters = false) //包扫描
        }
)
//FilterType.ANNOTATION 按照注解
//FilterType.ASSIGNABLE: 按照给定的类型
//FilterType.ASPECTJ,使用ASPECTJ表达式
//FilterType.REGEX; 正则表达式
//FilterType.CUSTOM ,客户自己定义,需要实现一个借口
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
