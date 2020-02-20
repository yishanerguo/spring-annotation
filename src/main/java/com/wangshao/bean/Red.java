package com.wangshao.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author liutao
 * @create 2020-02-19-15:32
 */

@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    //得到ioc容器
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("传入的ioc:"+ applicationContext);
        this.applicationContext = applicationContext;
    }

    //当前bean的名字
    public void setBeanName(String name) {

        System.out.println("当前bean的名字:"+ name);
    }

    //解析值
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolvestringvalu = resolver.resolveStringValue("你好${os.name},我是#{20*18}");
        System.out.println("解析字符串:"+resolvestringvalu);
    }
}
