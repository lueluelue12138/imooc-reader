package com.imooc.reader.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {
    @Autowired
    private Producer kaptchaProducer;
    @GetMapping("/api/verify_code")
    public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        设置清空缓存和让缓存立即失效，确保每次请求都能刷新验证码
//响应立即过期
        response.setDateHeader("Expires" , 0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control" , "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control" ,"post-check=0,pre-check=0");
        response.setHeader("Pragma" , "no-cache");
//        设置响应内容类型
        response.setContentType("image/png");
        //生成验证码图片
        String text = kaptchaProducer.createText();
        request.getSession().setAttribute("kaptchaVerifyCode" , text);
        BufferedImage image = kaptchaProducer.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
