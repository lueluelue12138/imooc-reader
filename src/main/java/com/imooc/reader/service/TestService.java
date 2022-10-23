package com.imooc.reader.service;

import com.imooc.reader.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class)
    public void batchImport(){
        for (int i = 0; i < 5; i++) {
            if (i==2){
                throw new RuntimeException("未处理异常");
            }
            testMapper.insertSample();
        }
    }
}
