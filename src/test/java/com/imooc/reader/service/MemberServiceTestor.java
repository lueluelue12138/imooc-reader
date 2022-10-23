package com.imooc.reader.service;

import com.imooc.reader.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

//将junit和spring框架整合
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTestor {
    @Autowired
    private MemberService memberService;
    @Test
    public void createMember1() {
        Member member = memberService.createMember("1232", "12323", "lueluelue");
        System.out.println(member);
    }
    @Test
    public void createMember2() {
        Member member = memberService.createMember("123", "12323", "lueluelue");
        System.out.println(member);
    }

    //登录成功
    @Test
    public void checkLogin1() {
        Member member = memberService.checkLogin("lueluelue", "123456");
        System.out.println(member);
    }
    //密码错误
    @Test
    public void checkLogin2() {
        Member member = memberService.checkLogin("lueluelue", "1234561");
        System.out.println(member);
    }
    //用户不存在
    @Test
    public void checkLogin3() {
        Member member = memberService.checkLogin("im1ooc_1", "123456");
        System.out.println(member);
    }
}