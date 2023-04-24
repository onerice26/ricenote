package priv.onerice.ricenote.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.security.JwtTokenUtil;
import priv.onerice.ricenote.utils.RedisUtil;
import priv.onerice.ricenote.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author onerice
 * @date 2023/4/22
 * @apiNote
 */
@Component
@Slf4j
public class JwtAuthSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        String token = JwtTokenUtil.createToken(auth.getName());
        RedisUtil.set(auth.getName(), token);
        response.setHeader(JwtTokenUtil.getAuthorization(), token);
        Map<Object, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, Result.success("登录成功", map));
    }
}
