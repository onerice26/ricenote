package priv.onerice.ricenote.security;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.handler.ex.ResultCode;
import priv.onerice.ricenote.utils.RedisUtil;
import priv.onerice.ricenote.utils.ResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author onerice
 * @date 2023/4/22
 * @apiNote 认证前的过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //请求头为 accessToken
        //请求体为 Bearer token
        String token = request.getHeader(JwtTokenUtil.getAuthorization());
        if (StringUtils.isNotBlank(token)) {
            String username;
            try {
                username = JwtTokenUtil.getUserNameFromToken(token);
            } catch (Exception e) {
                ResponseUtil.out(response, Result.failed(ResultCode.USER_NOT_TOKEN));
                return;
            }
            String redis_token = RedisUtil.get(token);
            if (Objects.isNull(username) || StringUtils.isBlank(redis_token)) {
                ResponseUtil.out(response, Result.failed(ResultCode.USER_NOT_TOKEN));
                return;
            }
            if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UserDetails userDetails = JSONObject.parseObject(redis_token, JwtUserDetails.class);
                if (Objects.nonNull(userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
