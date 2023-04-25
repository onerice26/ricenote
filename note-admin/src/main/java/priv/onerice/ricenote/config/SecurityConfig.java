package priv.onerice.ricenote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import priv.onerice.ricenote.security.JwtAuthenticationDetailsSource;
import priv.onerice.ricenote.security.JwtAuthenticationEncoder;
import priv.onerice.ricenote.security.JwtAuthenticationTokenFilter;
import priv.onerice.ricenote.security.JwtUserDetailsService;
import priv.onerice.ricenote.security.handler.*;

/**
 * @author onerice
 * @date 2023/3/29
 * @apiNote
 */
@Configurable
@EnableWebSecurity
// 开启方法级别的权限认证
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthSuccessHandler successHandler;
    @Autowired
    private JwtAuthFailureHandler failureHandler;
    @Autowired
    private JwtLogoutHandler logoutHandler;
    @Autowired
    private JwtAuthorizedEntryPoint unauthorizedEntryPoint;
    @Autowired
    private JwtAuthDeniedHandler deniedHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private JwtAuthenticationDetailsSource jwtAuthenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(new JwtAuthenticationEncoder(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * antMatchers: ant的通配符规则
         * ? 匹配任何单字符
         * * 匹配0或者任意数量的字符，不包含"/"
         * ** 匹配0或者更多的目录，包含"/"
         */
//        http
//                .headers()
//                .frameOptions().disable();

        http
                //登录后,访问没有权限处理类
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                //匿名访问,没有权限的处理类
                .authenticationEntryPoint(unauthorizedEntryPoint);

        //使用jwt的Authentication,来解析过来的请求是否有token
        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                //这里表示"/any"和"/ignore"不需要权限校验
                .antMatchers("/ignore/**", "/captcha", "/login", "/**/register/**").permitAll()
                .anyRequest().authenticated()
                // 这里表示任何请求都需要校验认证(上面配置的放行)


                .and()
                //配置登录,检测到用户未登录时跳转的url地址,登录放行
                .formLogin()
                .authenticationDetailsSource(jwtAuthenticationDetailsSource)
                //需要跟前端表单的action地址一致
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()

                //配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //配置登出,登出放行
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutHandler)
                .permitAll()

                .and()
                .csrf().disable();
    }

   /* @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index**", "/api/**", "/swagger-ui.html/**");
    }
}
