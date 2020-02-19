package com.wangshao.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liutao
 * @create 2020-02-19-15:00
 */

//判断是否linux系统
public class WindowsCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String windows = environment.getProperty("os.name");
        if (windows.contains("Windows")) {
            return true;
        }

        return false;
    }
}
