package com.wangshao.dao;

import org.springframework.stereotype.Repository;

/**
 * @author liutao
 * @create 2020-02-18-16:48
 */
//名字默认是类名首字母小写
@Repository
public class BookDao {

    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
