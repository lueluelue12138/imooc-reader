package com.imooc.reader.task;

import com.imooc.reader.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ComputeTask {
    @Autowired
    private BookService bookService;
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);

    @Scheduled(cron = "0 * * * * ?")
    public void updateSource() {
        bookService.updateScore();
        logger.info("已更新所有图书评分");
    }
}
