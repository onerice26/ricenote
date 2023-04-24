package priv.onerice.ricenote.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.utils.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author onerice
 * @date 2023/4/22
 * @apiNote 登录失败处理类
 */
@Slf4j
@Component
public class JwtAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        ResponseUtil.out(response, Result.error(exception.getMessage()));
    }
}
