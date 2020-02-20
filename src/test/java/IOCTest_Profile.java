import com.wangshao.bean.Boss;
import com.wangshao.bean.Car;
import com.wangshao.bean.Color;
import com.wangshao.bean.Yellow;
import com.wangshao.config.MainConfigOfAutowired;
import com.wangshao.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author liutao
 * @create 2020-02-20-0:24
 */


public class IOCTest_Profile {
    //1.使用命令行动态参数:-Dspring.profiles.active=test
    //2.代码方式
    @Test
    public void  test01(){
        //1.创建一个applicationcontext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2.设置需要激活环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        //3.注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4.启动刷新容器
        applicationContext.refresh();

        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : namesForType) {
            System.out.println(name);
        }

        Yellow ye = applicationContext.getBean(Yellow.class);
        System.out.println(ye);
        applicationContext.close();
    }
}
