package priv.onerice.ricenote.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.handler.ex.ResultCode;
import priv.onerice.ricenote.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author onerice
 * @date 2023/4/16
 * @apiNote 匿名未登录的时候访问，需要登录的资源的调用类
 */
@Slf4j
@Component
public class JwtAuthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, Result.error(ResultCode.USER_NOT_LOGIN));
    }
}
