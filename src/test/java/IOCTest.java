import com.wangshao.config.MainConfig;
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
}
