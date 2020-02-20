package com.wangshao.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author liutao
 * @create 2020-02-20-20:49
 */

@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution(public int com.wangshao.aop.MathCalculator.div(int,int))")
    public void pointCut(){

    }


    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"运行.....before参数列表是:{}"+ Arrays.asList(args));
    }

    @After("pointCut()")
    public void  logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"除法结束.......after");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void  logReturn(Object result){
        System.out.println("除法正常返回.....afterReturning运行结果:{"+result+"}");
    }
    //JoinPoint一定要出现参数表的第一位
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"除法异常.....afterThrowing异常信息:{"+exception+"}");
    }
}
