package com.wangshao.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author liutao
 * @create 2020-02-18-15:49
 */


public class Person {
    /**
     *  使用@value赋值:
     *      1.基本数值
     *      2.可以写SpEL: #{}
     *      3.可以写${},去除配置文件中值
     */

    @Value("张三")
    private  String name;
    @Value("#{20-2}")
    private  Integer age;

    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        super();
    }
}
