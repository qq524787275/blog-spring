package com.zhuzichu.blog.configure;

import com.zhuzichu.blog.filter.JwtAuthenticationFilter;
import com.zhuzichu.blog.filter.JwtLoginFilter;
import com.zhuzichu.blog.service.CustomAuthenticationProvider;
import com.zhuzichu.blog.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    @Autowired
    UserDetailsServiceImpl detailsService;
    @Override
    public void configure(WebSecurity web) throws Exception {
        logger.info("1111111111111111");
        web.ignoring().antMatchers(new String[]{"/js/**","/css/**","/img/**","/images/**","/fonts/**","/layui/**"});
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("2222222222222222");
        //解决不允许显示在iframe的问题
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.authenticationProvider(new CustomAuthenticationProvider(detailsService,bCryptPasswordEncoder));
        super.configure(auth);
    }
}
