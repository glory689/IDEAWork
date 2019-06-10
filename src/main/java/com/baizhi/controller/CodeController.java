package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

//绘制验证码
@RestController
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpSession session, HttpServletResponse response) {
        //1.绘制图片中的数字
        String securityCode = ValidateImageCodeUtils.getSecurityCode();//4位数
        session.setAttribute("securityCode", securityCode);
        //2.绘制验证码图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);

        //3.写出  1.图片  2.图片的格式  3.输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
