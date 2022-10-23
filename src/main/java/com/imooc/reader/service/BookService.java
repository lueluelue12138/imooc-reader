package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;

import java.util.Map;


public interface BookService {

    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows);

    /**
     * 查询图书详情
     * @param bookId 图书编号
     * @return 图书对象
     */
    Book selectById(Long bookId);

    /**
     * 更新图书评分及评分人数
     */
    void updateScore();

    /**
     * 图书管理界面查询图书列表
     * @param page 查询的页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    IPage<Map> selectBookMap(Integer page,Integer rows);

    /**
     * 图书管理界面新增图书
     * @param book 前台传入的新增图书对象
     * @return 图书对象
     */
    public Book createBook(Book book);

    /**
     * 图书管理界面更新图书
     * @param book 前台传入的修改图书对象
     * @return 图书对象
     */
    public Book updateBook(Book book);

    /**
     * 图书管理界面删除图书
     * @param bookId 删除图书Id
     */
    public void deleteBook(Long bookId);

}
