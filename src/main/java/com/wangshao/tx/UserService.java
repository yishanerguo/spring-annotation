package com.wangshao.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liutao
 * @create 2020-02-21-21:04
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public  void  insertUser(){
        userDao.insert();
        System.out.println("插入完成");
        int i = 10/0;

    }
}
