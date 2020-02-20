package com.wangshao.config;

import com.wangshao.aop.LogAspects;
import com.wangshao.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liutao
 * @create 2020-02-20-18:57
 *
 * AOP: 动态代理
 *     指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *  1.导入aop模块: spring AOP:  (spring-aspects)
 *  2.定义一个业务逻辑类:在业务逻辑运行的时候将日志进行打印(方法之前,方法运行结束,方法异常)
 *  3.定义一个日志切面类(LogAspects),切面类里面的方法需要动态感知MathCalculator.div运行到哪
 *      通知方法:
 *           前置通知:@Before,目标方法运行之前运行
 *           后置通知:@After目标方法运行结束之后运行(无论是正常结束或异常结束)
 *           返回通知:@AfterReturning目标方法正常返回之后运行
 *           异常通知:@AfterThrowing目标方法出现异常以后运行
 *           环绕通知:@Around动态代理,手动推进目标方法运行(joinPoint.procced())
 *
 *   4.g给切面类的方法标注何时何地运行
 *   5.将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 *   6.告诉spring哪个类是切面类(@Aspect)
 *   7.给配置类加上@EnableAspectjAutoProxy
 *       在spring中很多的@Enablexxxxx,开启....
 *
 *   三步:
 *      1.将业务逻辑组件和切面组件加入到容器中,告诉spring哪个是切面类(@Aspect)
 *      2.在切面类上的每个通知方法上标注通知注解,告诉spring何时何地运行(切入点表达式)
 *      3.开启基于注解的aop模式
 *
 *   AOP原理:[看给容器中注册了什么组件,这个组件什么时候工作,这个组件功能]
 *       @EnableAspectJAutoProxy:
 *   1.@EnableAspectJAutoProxy是什么?
 *          @Import(AspectJAutoProxyRegistrar.class),给容器中导入AspectJAutoProxyRegistrar
 *            internalAutoProxyCreator =AnnotationAwareAspectJAutoProxyCreator
 *       给容器中注册一个AnnotationAwareAspectJAutoProxyCreator(自动代理装配器)
 *   2.AnnotationAwareAspectJAutoProxyCreator:
 *      AnnotationAwareAspectJAutoProxyCreator
 *        -->AspectJAwareAdvisorAutoProxyCreator
 *          -->AbstractAdvisorAutoProxyCreator
 *            -->AbstractAutoProxyCreator
 *               implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                  关注后置处理器(在bean初始化完成前后座事情),自动装配BeanFactory
 *
 *   3.AbstractAutoProxyCreator.
 *   AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory);
 *   AbstractAutoProxyCreator有后置处理器的逻辑
 *
 *   AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)--initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *   AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *   流程:
 *      1.传入配置类,创建ioc容器
 *      2.注册配置类,调用refresh()刷新容器
 *      3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
 *            1>,先获取ioc容器已经定义了需要创建对象的所有BeanPostProcessor
 *            2>给容器中加别的BeanPostProcessor
 *            3>优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *            4>在给容器中注册实现了Ordered接口的BeanPostProcessor
 *            5>注册没实现优先级接口的BeanPostProcessor
 *            6>注册BeanPostProcessor,实际上就是创建BeanPostProcessor对象,保存在容器中
 *               创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *               1.创建bean实例
 *               2.populateBean,给bean的各种属性赋值
 *               3.initializeBean:初始化bean
 *                      1.invokeAwareMethods(),处理Aware接口的方法回调
 *                      2.applyBeanPostProcessorsBeforeInitialization(),应用后置处理器的postProcessAfterInitialization(),
 *                      3.invokeInitMethods();执行自定义的初始化方法
 *                      4.applyBeanPostProcessorsAfterInitialization（），应用后置处理器的postProcessAfterInitialization()
 *               4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功--aspectJAdvisorsBuilder
 *            7>把BeanPostProcessor注册到BeanFacotry中,
 *                      beanFactory.addBeanPostProcessor(postProcessor);
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    //业务逻辑加入容器中
    @Bean
    public MathCalculator  mathCalculator(){
        return  new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
