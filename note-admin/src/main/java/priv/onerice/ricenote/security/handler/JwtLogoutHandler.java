package priv.onerice.ricenote.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.security.JwtTokenUtil;
import priv.onerice.ricenote.utils.RedisUtil;
import priv.onerice.ricenote.utils.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        String token = request.getHeader(JwtTokenUtil.getAuthorization());
        if (StringUtils.isNotBlank(token)) {
            RedisUtil.delete(token);
        }
        ResponseUtil.out(response, Result.success("退出成功"));
    }
}
