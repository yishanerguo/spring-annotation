import com.wangshao.bean.Person;
import com.wangshao.config.MainConfig;
import com.wangshao.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author liutao
 * @create 2020-02-18-16:56
 */


public class IOCTest {

    @Test
    public  void  test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name : beanDefinitionNames){
            System.out.println(name);
        }
    }

    @Test
    public  void test02() {
       ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name : beanDefinitionNames){
            System.out.println(name);
        }
        //根据id获取实例
        System.out.println("ioc容器创建完成");
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }

    @Test
    public  void test03(){
       ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
       //获取所有创建这个实例id
       String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        //获取电脑的运行环境
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }
}
