package priv.onerice.ricenote.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @date 2023/4/22
 * @apiNote
 */
@Component
@Slf4j
public class JwtAuthDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.out(response, Result.failed(ResultCode.NO_PERMISSION));
    }
}
