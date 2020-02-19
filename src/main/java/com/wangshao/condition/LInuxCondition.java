package com.wangshao.condition;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liutao
 * @create 2020-02-19-14:59
 */

//判断是否linux系统
public class LInuxCondition implements Condition {

    /**
     *
     * @param context:判断条件能使用的上下文(环境)
     * @param metadata:注释信息(标注conditional注解信息)
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
       //是否为linux系统\
        //获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取电脑当前环境
        Environment environment = context.getEnvironment();
        //获取到bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");
        if(property.contains("linux")){
            return true;
        }
        return false;
    }
}
