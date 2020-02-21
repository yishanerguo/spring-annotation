import com.wangshao.aop.MathCalculator;
import com.wangshao.bean.Yellow;
import com.wangshao.config.MainConfigOfAOP;
import com.wangshao.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTest_AOP {

    @Test
    public void  test01(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1.不要自己创建对象
//        MathCalculator mathCalculator = new MathCalculator();
//        mathCalculator.div(1,1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1, 1);

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
