import com.wangshao.aop.MathCalculator;
import com.wangshao.config.MainConfigOfAOP;
import com.wangshao.tx.TxConfig;
import com.wangshao.tx.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTest_Tx {

    @Test
    public void  test01(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService userService = applicationContext.getBean(UserService.class);

        userService.insertUser();
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
