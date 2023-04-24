package priv.onerice.ricenote.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysUserService;

@Component
@Slf4j
public class JwtAuthProvider implements AuthenticationProvider {
    @Autowired
    private ISysUserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String account = auth.getName();
        String password = (String) auth.getCredentials();
        SysUser sysUser = userService.getUserByName(account);
        if (ObjectUtils.isEmpty(sysUser)) {
            log.info("登录用户：{} 不存在", account);
            throw new UsernameNotFoundException("登录用户：" + account + " 不存在");
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(account, password);
        result.setDetails(auth.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthProvider.class.isAssignableFrom(authentication);
    }
}
