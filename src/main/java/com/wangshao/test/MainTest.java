package com.wangshao.test;

import com.wangshao.bean.Person;
import com.wangshao.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.text.StyledEditorKit;

/**
 * @author liutao
 * @create 2020-02-18-15:57
 */


public class MainTest {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        Person bean = (Person) applicationContext.getBean("person");
//        System.out.println(bean);

        //注解式
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        //类型获取
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(person.getClass());

        for(String name : beanNamesForType){
            System.out.println(name);
        }
    }

}
