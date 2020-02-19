import com.wangshao.bean.Person;
import com.wangshao.config.MainConfigOfAutowired;
import com.wangshao.config.MainConfigOfPropertyValues;
import com.wangshao.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTestAutowired {

    @Test
    public void  test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        System.out.println("容器创建完成");

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
