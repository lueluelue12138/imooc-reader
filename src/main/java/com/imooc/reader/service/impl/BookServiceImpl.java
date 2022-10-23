package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private MemberReadStateMapper memberReadStateMapper;

    /**
     * 分页查询图书
     *
     * @param categoryId 分类编号
     * @param order      排序方式
     * @param page       页号
     * @param rows       每页记录数
     * @return 分页对象
     */
    @Override
    public IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p = new Page<>(page, rows);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (categoryId != null && categoryId != -1) {
            wrapper.eq("category_id", categoryId);
        }
        if (order != null) {
            if (order.equals("quantity")) {
                wrapper.orderByDesc("evaluation_quantity");
            } else if (order.equals("score")) {
                wrapper.orderByDesc("evaluation_score");
            }
        } else {
            wrapper.orderByDesc("evaluation_quantity");
        }
        return bookMapper.selectPage(p, wrapper);
    }

    @Override
    public Book selectById(Long bookId) {
        return bookMapper.selectById(bookId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateScore() {
        bookMapper.updateScore();
    }

    @Override
    public IPage<Map> selectBookMap(Integer page, Integer rows) {
        IPage p =new Page(page,rows);
        p=bookMapper.selectBookMap(p);
        return p;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("book_id",bookId);
        evaluationMapper.delete(wrapper1);
        memberReadStateMapper.delete(wrapper1);
    }
}
