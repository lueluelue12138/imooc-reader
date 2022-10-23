package com.imooc.reader.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;

import java.util.Map;

@SuppressWarnings("MybatisXMapperMethodInspection")
public interface BookMapper extends BaseMapper<Book> {
    void updateScore();

    IPage<Map> selectBookMap(IPage page);
}
