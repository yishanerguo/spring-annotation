package com.wangshao.aop;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * @author liutao
 * @create 2020-02-20-20:44
 */


public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("MathCalculator.....被调用");
        return  i/j;
    }
}
