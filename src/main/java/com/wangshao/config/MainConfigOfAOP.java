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
 *
 *    =========================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=====================
 *    AnnotationAwareAspectJAutoProxyCreator==<InstantiationAwareBeanPostProcessor
 *        4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作,创建剩下单实例bean
 *                1.遍历获取容器中所有的Bean:依次创建对象getBean(beanName);
 *                      getBean-->doGetBean()--<getSingleton()-->
 *                2.创建bean
 *                      [AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截,如果是InstantiationAwareBeanPostProcessor,会调用postProcessBeforeInstantiation]
 *                      1.先从缓存中获取当前的bean,如果能获取到,说明bean是之前被创建过的;直接使用,否则在创建
 *                             只要创建好的Bean都会被缓存起来
 *                      2.createBean();创建bean;AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean实例
 *                          [BeanPostProcessor实在Bean对象创建完成初始化前后调用的]
 *                          [InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的]
 *                          1.resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                            希望后置戳力气再次能返回一个代理对象,如果能返回代理对象就使用,如果不能就继续
 *                             1.后置处理器先尝试返回对象
 *                               bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                               拿到所有后置处理器,如果是InstantiationAwareBeanPostProcessor
 *                               就执行postProcessBeforeInstantiation
 * 					             if (bean != null) {
 * 						         bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 					             }
 *                          2.doCreateBean(beanName, mbdToUse, args);真正的创建一个bean实例,和3.6流程一样
 *                          3.
 *
 *
 *    AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用:
 *      1.每个bean'创建之前,调用postProcessBeforeInstantiation():
 *           关系MathCalulator和LogAspect的创建
 *           1.判断当前bean时候在advisedBeans中(保存了所有需要增强bean)
 *           2.判断当前bean时候是基础类型的Advice||Pointcut||Advisor|AopInfrastructureBean或者是切面(@Aspect)
 *           3.是否需要跳过
 *               1.获取候选的增强器(切面的通知方法)[List<Advisor> candidateAdvisors]
 *                  每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *                  判断每一个增强器是否是AspectJPointcutAdvisor
 *               2.永远返回false
 *  2.创建对象
 *  postProcessAfterInitialization:
 *     wrapIfNecessary(bean, beanName, cacheKey);//如果需要包装的话
 *      1.获取当前bean的所有增强器(通知方法)  Object[]    specificInterceptors
 *             1.找到候选的所有的增强器(找哪些通知方法是需要切入当前bean方法的)
 *             2.获取到能在bean使用的增强器
 *             3.给增强器排序
 *      2.保存当前bean在advisedBeans中:
 *      3.保存当前bean需要增强,创建bean的代理对象
 *          1.获取所有增强器(通知方法)
 *          2.保存到proxyFactory中
 *          3.创建代理对象:spring自动决定
 *                JdkDynamicAopProxy(config):jdk动态代理
 *                ObjenesisCglibAopProxy(config):cglib的动态代理
 *       4.给容器中返回当前组件使用cglib增强的代理对象:
 *       5.以后容器中获取到的就是这个组件的代理对象,执行目标方法的时候,代理对象就会执行通知方法的流程\
 *
 *
 *   3.目标方法的执行:
 *         容器保存了组件的代理对象(cglib增强后的对象),这个对象里面保存了详细信息(比如增强器,目标对象,xxxx)
 *         1.CglibAopProxy.intercept();拦截目标方法的执行
 *         2.根据ProxyFactory对象将要执行目标方法获取拦截器链:
 *         				List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *                      1.创建一个List<Object> interceptorList保存所有拦截器5
 *                         一个默认的ExposeInvocationInterceptor和4个增强器
 *                      2.遍历所有的增强器,将其转成registry.getInterceptors(advisor);
 *                      3.将增强器转为List<MethodInterceptor>
 *                          如果是MethodInterceptor,直接加入到集合中
 *                          如果不是,使用AdvisorAdapter将增强器转为MethodInterceptor
 *                          转换完成返回MethodInterceptor数组
 *         3.如果没有拦截器链,直接执行目标方法
 *           连接器链(每个通知方法又包装为方法拦截器,利用MethodInterceptor机制)
 *         4.如果有拦截器链,把需要执行的目标对象,目标方法,拦截器连等信息,传入创建一个CglibMethodInvocation对象,
 *             并调用proceed();返回一个retVal
 *         5.连接器链的触发过程
 *             1.如果没有拦截器执行目标方法,或者拦截器的索引和拦截器数组-1大小一样(指定到最后一个拦截器)
 *             2.链式获去每个拦截器,拦截器执行invoke方法,每个拦截器等待下一个拦截器执行完成返回以后再来执行
 *                拦截器链的机制,保证通知方法和执行方法的顺序
 *
 *
 *   总结:
 *      1.@EnableAspectjAutoProxy 开始AOP功能
 *      2.@EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectAutoProxyCreator
 *      3.AnnotationAwareAspectAutoProxyCreator是一个后置处理器,
 *      4.容器的创建流程:
 *            1.registerBeanPostProcessors() 注册后置处理器,创建AnnotationAwareAspectAutoProxyCreator是一个后置处理器
 *            2.finishBeanFacoryInitalization() 初始化剩下的单实例bean
 *                1.创建业务逻辑组件和切面组件
 *                2.AnnotationAwareAspectAutoProxyCreator拦截组件的创建过程
 *                3.在组件创建完之后,判断组件是否需要增强
 *                    是,切面的通知方法,包装成增强器(Advisor),给业务逻辑创建一个代理对象(cglib)
 *      5.执行目标方法:
 *            1.代理对象执行目标方法
 *            2.CglibAopProxy.intercept():
 *                 1.得到目标方法的拦截器链(增强器包装成拦截器MethodInterceptor)
 *                 2.利用连接器的链式机制,一次进去每个连接器进行执行
 *                 3.效果:
 *                   正常执行: 前置通知-目标方法-后置通知-返回通知
 *                   出现异常: 前置通知-目标方法-后置通知-异常通知
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
