package com.imooc.reader.mapper;

import com.imooc.reader.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
//将junit和spring框架整合
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EvaluationMapperTestor {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Test
    public void selectByBookId() {
        List<Map> maps = evaluationMapper.selectByBookId(2L);
        System.out.println(maps);
    }
}