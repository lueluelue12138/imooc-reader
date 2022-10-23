package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.junit.Assert.*;
//将junit和spring框架整合
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceTestor {
    @Autowired
    private BookService bookService;
    @Test
    public void selectPage() {
        IPage<Book> bookIPage = bookService.selectPage(null, null, 1, 5);
        List<Book> bookList = bookIPage.getRecords();
        for (Book b:bookList
             ) {
            System.out.println(b.getBookId()+"  "+b.getBookName());
            System.out.println("总页数："+bookIPage.getPages());
            System.out.println("总记录数："+bookIPage.getTotal());
        }
    }
}