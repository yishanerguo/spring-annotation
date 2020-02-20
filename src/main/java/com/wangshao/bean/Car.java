package com.wangshao.bean;

import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-19-17:52
 */

@Component
public class Car {

    public Car() {
        System.out.println("car constructor......");
    }

    public  void  init(){
        System.out.println("car ..... init.....");
    }

    public  void  detory(){
        System.out.println("car..... destory.....");
    }
}
