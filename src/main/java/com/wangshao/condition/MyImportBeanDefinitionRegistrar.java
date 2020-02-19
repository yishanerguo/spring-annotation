package com.wangshao.condition;

import com.wangshao.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liutao
 * @create 2020-02-19-15:52
 */


public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata:当前类的注解信息
     * @param registry:BeanDefinition注册类
     *                把所有需要添加到容器中的bean:调用
     *                BeanDefinitionRegistry.registerBeanDefiniton
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean red = registry.containsBeanDefinition("com.wangshao.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.wangshao.bean.Blue");
        if (red && blue) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow",rootBeanDefinition);
        }
    }
}
