package com.imooc.reader.service;

import com.imooc.reader.entity.Category;

import java.util.List;

public interface CategoryService {
     /**
      * 查询所有图书分类
      * @return 分类列表
      */
     List<Category> selectAll();
}
