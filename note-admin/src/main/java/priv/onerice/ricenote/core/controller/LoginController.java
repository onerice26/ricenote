package priv.onerice.ricenote.core.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.base.ResultCode;
import priv.onerice.ricenote.base.RiceConst;
import priv.onerice.ricenote.base.vo.LoginUserDTO;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysUserService;
import priv.onerice.ricenote.handler.ex.RiceException;
import priv.onerice.ricenote.utils.PasswordUtil;
import priv.onerice.ricenote.utils.RSAUtils;
import priv.onerice.ricenote.utils.RedisUtil;
import priv.onerice.ricenote.utils.ServletUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Api(tags = "用户登录")
public class LoginController {

    @Value("${rice.encrypt.rsa.priv-key}")
    public String rsaPriv;

    @Autowired
    private ISysUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public Result login(@RequestBody LoginUserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "账号或密码为空");
        }
        if (StringUtils.isBlank(userDTO.getUsername())
                || StringUtils.isBlank(userDTO.getPassword())) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "账号或密码为空");
        }
        /*if (StringUtils.isBlank(userVo.getCaptcha())) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "验证码为空");
        }*/
        /*String capt = RedisUtil.get(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + userVo.getCaptcha());
        if (!StringUtils.equals(capt, userVo.getCaptcha())) {
            return Result.failed(ResultCode.PARAM_IS_BLANK.getCode(), "验证码不正确");
        }*/
        SysUser sysUser = userService.getUserByName(userDTO.getUsername());
        if (Objects.isNull(sysUser)) {
            return Result.failed(ResultCode.USER_USERNAME_PASSWORD_ERROE);
        }
        // 加密匹配
        String password;
        try {
            password = RSAUtils.decryptRSADefault(rsaPriv, userDTO.getPassword());
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            log.error("解密算法执行失败：", e);
            return Result.failed(ResultCode.USER_USERNAME_PASSWORD_ERROE);
        }
        String encrypt = SaSecureUtil.md5BySalt(SaSecureUtil.md5(userDTO.getUsername() + "&" + password), sysUser.getSalt());
        String encrypt1 = PasswordUtil.decrypt(sysUser.getPassword());
        if (!StringUtils.equals(encrypt1, encrypt)) {
            return Result.failed(ResultCode.USER_USERNAME_PASSWORD_ERROE);
        }
        StpUtil.login(sysUser.getId());
        String token = StpUtil.getTokenValue();
//        RedisUtil.delete(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + userVo.getCaptcha());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("userInfo", sysUser);
        return Result.success("登录成功", map);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出", notes = "退出")
    public Result logout(HttpServletRequest request) {
        String token = ServletUtil.getToken(request);
        if (StringUtils.isBlank(token)) {
            return Result.failed(ResultCode.USER_NOT_TOKEN);
        }
        String key1 = "ricenote:login:token:" + token;
        String s = RedisUtil.get(key1);
        String key2 = "ricenote:login:session:" + s;
        RedisUtil.delete(key1);
        RedisUtil.delete(key2);
        StpUtil.logout();
        return Result.success("退出成功", null);
    }

    @GetMapping("/captcha")
    @ApiOperation(value = "图形验证码", notes = "图形验证码")
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

