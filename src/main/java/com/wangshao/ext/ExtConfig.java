package com.wangshao.ext;

import com.wangshao.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @create 2020-02-21-23:07
 *
 * 扩展原理:
 *
 *  BeanPostProcessor:bean后置处理器,bean创建对象初始化前后进行拦截工作的
 *  BeanFactoryPostProcessor:beanFactory的后置处理器
 *        在BeanFactory标准初始化之后调用:所有的bean定义已经保存加载到beanFactory,但是bean的实例还未创建
 *
 *   1.ioc容器创建对象
 *   2.	invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessors
 *     如何找到所有的BeanFactoryPostProcessor并执行他们饿的方法
 *        1.直接在beanfacory中找到所有类型是BeanFacoryPostProcessor的组件,并执行他们的方法
 *        2.在初始化创建其他组件的前面
 *
 *  2.BeanDefinitionRegistryPostProcessor
 *       postProcessBeanDefinitionRegistry()
 *       在所有bean定义信息将要被加载,bean实例还未创建的
 *
 *       有限与BeanFactoryPostProcessor执行,
 *       利用BeanDefinitionRegistryPostProcessor容器中再额外添加一些组件
 *
 *   原理:
 *     1.创建ioc容器
 *     2.refresh()---invokeBeanFactoryPostProcessors(beanFactory);
 *     3.从容器中获取所有的BeanDefinitionRegistryPostProcessor组件,
 *        1.依次触发所有的postProcessBeanDefinitionRegistry()方法
 *        2.再来触发postProcessBeanFactory()方法[BeanDefinitionRegistryPostProcessor]
 *     4.再来从容器中找到BeanFactoryPostProcessor组件,然后依次触发postProcessBeanFacotry()方法
 *
 *  3.ApplicationListener:监听容器中发布的事件,事件驱动模型开发
 *        public interface ApplicationListener<E extends ApplicationEvent>
 *            监听ApplicationEvent及其下面的子事件
 *
 *    步骤:
 *      1.写一个监听器来监听某个事件(ApplicationEvent及其子类)
 *      2.把监听器加入容器
 *      3.只要容器中相关事件发布,我们就能监听到这个事件
 *           ContextRefreshedEvent,容器刷新完成(所有bean都完全创建),会发布这个事件
 *           ContextCloseEvent,关系容器会发布这个事件
 *      4,发布一个事件
 *          applicationContext.publishEvent();
 */

@ComponentScan("com.wangshao.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return  new Blue();
    }
}
