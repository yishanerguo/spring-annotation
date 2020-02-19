import com.wangshao.bean.Person;
import com.wangshao.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTestProperty {

    @Test
    public void  test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
