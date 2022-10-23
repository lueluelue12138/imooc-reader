package com.imooc.reader.controller;

import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.service.EvaluationService;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/register")
    public ResponseUtils register(String username, String password, String nickname, String vc, HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        } else {
            //验证码验证成功后进行用户注册
            try {
                Member member = memberService.createMember(username, password, nickname);
                resp = new ResponseUtils();
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            }
        }
        return resp;
    }

    @PostMapping("/check_login")
    public ResponseUtils checkLogin(String username, String password, String vc, HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        } else {
            //验证码验证成功后进行用户登录
            try {
                Member member = memberService.checkLogin(username, password);
                member.setSalt(null);
                member.setPassword(null);
                resp = new ResponseUtils().put("member", member);
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            }
        }
        return resp;
    }

    @GetMapping("/select_by_id")
    public ResponseUtils selectById(Long memberId) {
        ResponseUtils resp;
        try {
            Member member = memberService.selectById(memberId);
            resp = new ResponseUtils().put("member", member);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @GetMapping("/select_read_state")
    public ResponseUtils selectMemberReadState(Long memberId, Long bookId) {
        ResponseUtils resp;
        try {
            MemberReadState memberReadState = memberService.selectMemberReadState(memberId, bookId);
            resp = new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/update_read_state")
    public ResponseUtils updateReadState(Long memberId,Long bookId,Integer readState){
        ResponseUtils resp;
        try {
            MemberReadState memberReadState = memberService.updateMemberReadState(memberId, bookId, readState);
            resp = new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/evaluate")
    public ResponseUtils evaluate(Long memberId,Long bookId,Integer score,String content){
        ResponseUtils resp;
        try {
            Evaluation evaluation = evaluationService.evaluation(memberId, bookId, score, content);
            resp = new ResponseUtils().put("evaluation", evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/enjoy")
    public ResponseUtils enjoy(Long evaluationId){
        ResponseUtils resp;
        try {
            Evaluation evaluation = evaluationService.enjoy(evaluationId);
            resp = new ResponseUtils().put("evaluation", evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}
