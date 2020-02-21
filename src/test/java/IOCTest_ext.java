import com.wangshao.aop.MathCalculator;
import com.wangshao.config.MainConfigOfAOP;
import com.wangshao.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTest_ext {

    @Test
    public void  test01(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {

        });

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
