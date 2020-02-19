package com.wangshao.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-19-18:52
 * 后置处理器:初始化前后进行处理工作
 * 将后置处理器加入容器中
 */

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessBeforeInitialization.."+bean+"====>"+beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization.."+bean+"====>"+beanName);
        return bean;
    }


}
