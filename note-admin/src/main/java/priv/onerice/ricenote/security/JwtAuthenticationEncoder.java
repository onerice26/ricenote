package priv.onerice.ricenote.security;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JwtAuthenticationEncoder extends DaoAuthenticationProvider {

    public JwtAuthenticationEncoder(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 可以在此处覆写整个登录认证逻辑
        return super.authenticate(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // 可以在此处覆写密码验证逻辑
        // 实现图形验证码逻辑
        JwtWebAuthenticationDetails details = (JwtWebAuthenticationDetails) authentication.getDetails();
        if (!details.getImageCodeIsRight()) {
            throw new BadCredentialsException("验证码错误");
        }
        //super.additionalAuthenticationChecks(userDetails, authentication);
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            String salt = ((JwtUserDetails) userDetails).getUser().getSalt();
            presentedPassword = DigestUtil.md5Hex(presentedPassword + salt);
            if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }
}
