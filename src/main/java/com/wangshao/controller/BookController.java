package com.wangshao.controller;

import com.wangshao.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author liutao
 * @create 2020-02-18-16:50
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
