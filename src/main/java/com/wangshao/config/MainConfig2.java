package com.wangshao.config;

import com.wangshao.bean.Person;
import com.wangshao.condition.LInuxCondition;
import com.wangshao.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author liutao
 * @create 2020-02-19-0:38
 */

@Configuration
public class MainConfig2 {

    //默认都是单实例(单例模式)
    @Bean("person")
   /** ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION  session
    *
    *  prototype:多实例:ioc容器启动并不会去调用方法创建对象放在容器中,每次获取的时候会调用方法创建 对象
    *  singleton:单实例(默认值) : ioc容器启动会调用方法创建对象放到ioc容器中,以后每次获取就是直接从容器中拿
    *  request:同一次请求创建一个实例
    *  session:同一个session创建一个实例
    *
    *  懒加载:
    *    单实例bean: 默认在容器启动的时候的创建对象;
    *    懒加载:容器启动不创建对象.第一次使用(获取)bean创建对象,并出初始化
    *
    */
    //@Scope("prototype")  //调整作用域
    @Scope
    @Lazy   //懒加载
    public Person person(){
        System.out.println("给容器中添加person......");
        return new Person("张三",25);
    }

    /**
     * @Conditional({condition}):按照一定的条件进行判断,满足条件给容器中注册bean
     *
     * 如果系统是windows,给容器中注册("bill")
     * 如果是linux系统,给容器中注册("linux")
     *
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public  Person person01(){
        return new Person("Bill Gates", 62);
    }

    @Conditional({LInuxCondition.class})
    @Bean("linux")
    public  Person person02(){
        return new Person("linux", 48);
    }
}
