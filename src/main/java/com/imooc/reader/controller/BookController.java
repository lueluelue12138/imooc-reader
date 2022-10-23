package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import com.imooc.reader.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtils list(Long categoryId, String order, Integer page) {
        ResponseUtils resp = null;
        try {
            IPage<Book> bookIPage = bookService.selectPage(categoryId, order, page, 10);
            resp = new ResponseUtils().put("page", bookIPage);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @GetMapping("/id/{id}")
    public ResponseUtils selectById(@PathVariable("id") Long id) {
        ResponseUtils resp = null;
        try {
            Book book = bookService.selectById(id);
            resp =new ResponseUtils().put("book",book);
        }catch (Exception e){
            e.printStackTrace();
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;

    }
}
