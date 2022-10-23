package com.imooc.reader.controller;

import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResponseUtils list(){
        //code="0"
        ResponseUtils resp =null;
        try {
            List<Category> categories = categoryService.selectAll();
            resp=new ResponseUtils().put("list",categories);
        }catch (Exception e){
            e.printStackTrace();
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
}
