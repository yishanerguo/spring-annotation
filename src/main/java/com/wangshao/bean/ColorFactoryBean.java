package com.wangshao.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author liutao
 * @create 2020-02-19-16:18
 */


public class ColorFactoryBean implements FactoryBean<Color> {

    /**
     * 返回一个color对象,这个对象会添加到容器中
     * @return
     * @throws Exception
     */
    public Color getObject() throws Exception {
        return new Color();
    }

    /**
     * 返回对象的类型
     * @return
     */
    public Class<?> getObjectType() {
        return Color.class;
    }
}
