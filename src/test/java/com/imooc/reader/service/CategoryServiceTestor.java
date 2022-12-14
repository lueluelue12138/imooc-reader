package com.imooc.reader.service;

import com.imooc.reader.entity.Category;
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
public class CategoryServiceTestor {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void selectAll() {
        List<Category> categories = categoryService.selectAll();
        for (Category c:categories
             ) {
            System.out.println(c);
        }
//        System.out.println(categories);
    }
}