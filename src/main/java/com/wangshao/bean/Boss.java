package com.wangshao.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-20-16:28
 */

@Component  //默认加载ioc容器中的组件,容器启动会调用调用无参构造器创建对象,在进行初始化赋值等操作
public class Boss {

    private Car car;

   /* @Autowired  //构造器要用的组件,都是容器中获取
    public Boss(Car car){
        this.car = car;
        System.out.println("boss.............有参构造器");
    }*/

   /*  //@Autowired放在参数上,都是容器中获取
    public Boss(@Autowired Car car){
        this.car = car;
        System.out.println("boss.............有参构造器");
    }*/
     //@Autowired可以省略,默认只有一个构造器时
    public Boss(Car car){
        this.car = car;
        System.out.println("boss.............有参构造器");
    }
    public Car getCar() {
        return car;
    }

   // @Autowired
    //标注在方法,spring容器创建当前对象,就会调用方法,完成赋值
    //方法使用的参数,自定义类型的值从IOC容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
