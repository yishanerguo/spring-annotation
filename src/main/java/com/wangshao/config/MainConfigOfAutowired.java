package com.wangshao.config;

import com.wangshao.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-20-1:54
 * 自动装配:
 *   spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 *   1>@autowired:自动注入:
 *       1>默认有限按照类型去容器中找对应的组件
 *       2>如果找到多个相同类型的组件,再将属性的名称作为组件的id去容器中查找
 *                          applicationContext.getBean("bookDao")
 *         BookService{
 *               @Autowired
 *               Bookdao bookdao;
 *         }
 *        3>@Qualifier("bookDao"):使用@Qualifier指定需要装配的组件的id,而不是使用属性名
 *        4>自动装配默认一定要将属性赋值好,没有就会报错:
 *                  可以使用@Autowired(required=false);
 *         5>@Primary  让spring进行自动装配的时候,默认使用首选的bean
 *                   也可以继续使用@Qualifier指定需要装配的bean的名字
 */

@Configuration
@ComponentScan({"com.wangshao.dao","com.wangshao.service","com.wangshao.controller"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }
}
