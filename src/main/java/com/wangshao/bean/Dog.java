package com.wangshao.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author liutao
 * @create 2020-02-19-18:42
 */

@Component
public class Dog implements ApplicationContextAware {

    private  ApplicationContext applicationContext;

    public  Dog(){
        System.out.println("dog constructor.....");

    }

    @PostConstruct
    public void  init(){
        System.out.println("dog......@PostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog...... @PreDestroy");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

         this.applicationContext = applicationContext;
    }
}
