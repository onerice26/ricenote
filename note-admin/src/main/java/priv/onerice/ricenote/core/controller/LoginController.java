package priv.onerice.ricenote.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.base.RiceConst;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysUserService;
import priv.onerice.ricenote.handler.ex.ResultCode;
import priv.onerice.ricenote.handler.ex.RiceException;
import priv.onerice.ricenote.utils.RedisUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/login")
    public Result Captcha(String account, String password, String captcha) {
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "账号或密码为空");
        }
        if (StringUtils.isBlank(captcha)) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "验证码为空");
        }
        String capt = RedisUtil.get(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + captcha);
        if (!StringUtils.equals(capt, captcha)) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "验证码不正确");
        }
        SysUser sysUser = userService.getUserByName(account);
        if (Objects.isNull(sysUser)) {
            return Result.failed(ResultCode.USER_ACCOUNT_PASSWORD_ERROE);
        }
        // 加密匹配
        /*String encrypt = PasswordUtil.encrypt(sysUser.getAccount() + sysUser.getSalt() + password);
        if (!StringUtils.equals(password, encrypt)) {
            return Result.failed(ResultCode.USER_ACCOUNT_PASSWORD_ERROE);
        }*/
        StpUtil.login(sysUser.getId());
        String token = StpUtil.getTokenValue();
        RedisUtil.delete(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + captcha);
        return Result.success("登录成功", token);
    }

    @GetMapping("/captcha")
    public void Captcha(HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 38, 4, 20);
            RedisUtil.set(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + captcha.getCode(), captcha.getCode(), 60, TimeUnit.SECONDS);
            captcha.write(outputStream);
        } catch (IOException e) {
            throw new RiceException("验证码获取失败");
        }
    }
}

