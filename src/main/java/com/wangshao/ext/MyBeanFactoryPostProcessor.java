package com.wangshao.ext;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author liutao
 * @create 2020-02-21-23:19
 */

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor.....postProcessBean");
        int count = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println("当前BeanFactory有"+count+"个Bean");
        System.out.println(Arrays.asList(beanDefinitionNames));
    }
}
