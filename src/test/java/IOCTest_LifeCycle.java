import com.wangshao.config.MainConfigOfLIfeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liutao
 * @create 2020-02-19-17:55
 */


public class IOCTest_LifeCycle {

    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLIfeCycle.class);
        System.out.println("容器创建完成");

       // Object car = applicationContext.getBean("car");
        //关闭容器
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
