package priv.onerice.ricenote.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.RiceConst;
import priv.onerice.ricenote.handler.ex.RiceException;
import priv.onerice.ricenote.utils.RedisUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {


    @GetMapping("/captcha")
    public void Captcha(HttpServletRequest request, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 38, 4, 20);
            String rand = request.getParameter("rand");
            RedisUtil.set(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + rand, captcha.getCode());
            captcha.write(outputStream);
        } catch (IOException e) {
            throw new RiceException("验证码获取失败");
        }
    }
}

