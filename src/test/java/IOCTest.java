import com.wangshao.config.MainConfig;
import com.wangshao.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
}
