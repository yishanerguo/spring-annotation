package com.wangshao.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @create 2020-02-22-1:42
 */

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    //当容器中发布此事件以后,方法触发
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件:"+event);

    }
}
