package com.wangshao.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 * @create 2020-02-22-21:19
 */

@Service
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public  void listener(ApplicationEvent applicationEvent){
        System.out.println("userservice....监听到的事件:"+ applicationEvent);
    }
}
