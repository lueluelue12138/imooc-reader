package com.imooc.reader.service;

import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.MemberReadStateMapper;

public interface MemberService {
    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 用户对象
     */
    Member createMember(String username,String password,String nickname);

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    Member checkLogin(String username,String password);

    /**
     * 根据Id查询用户（首页展示用户登录状态信息）
     * @param memberId 用户编号
     * @return 用户对象
     */
    Member selectById(Long memberId);

    /**
     * 查询用户阅读状态
     * @param memberId 用户编号
     * @param bookId 图书编号
     * @return 用户阅读状态对象
     */
    MemberReadState selectMemberReadState(Long memberId,Long bookId);

    /**
     * 更新用户阅读状态
     * @param memberId 用户编号
     * @param bookId 图书编号
     * @param readState 阅读状态
     * @return 用户阅读状态对象
     */
    MemberReadState updateMemberReadState(Long memberId,Long bookId,Integer readState);
}
