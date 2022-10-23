package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author D
* @description 针对表【evaluation】的数据库操作Service
* @createDate 2022-09-27 17:15:50
*/
public interface EvaluationService {

    /**
     * 根据图书名字查询评价列表和昵称
     * @param bookId 图书编号
     * @return 评价列表
     */
    public List<Map> selectByBookId(Long bookId);

    /**
     * 新增评价
     * @param memberId 用户编号
     * @param bookId 图书编号
     * @param score 评分
     * @param content 评论内容
     * @return 评论对象
     */
    public Evaluation evaluation(Long memberId,Long bookId,Integer score,String content);

    /**
     * 评论点赞
     * @param evaluationId 评论编号
     * @return 评论对象
     */
    public Evaluation enjoy(Long evaluationId);
}
