package com.wangshao.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-19-18:24
 */

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("Cat...........constructor");
    }

    public void destroy() throws Exception {
        System.out.println("Cat..........destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat..........init");
    }
}
