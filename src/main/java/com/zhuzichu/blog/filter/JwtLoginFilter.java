package com.zhuzichu.blog.filter;

import com.zhuzichu.blog.model.User;
import com.zhuzichu.blog.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       logger.info("attemptAuthentication:username="+ request.getParameter("username"));
        logger.info("attemptAuthentication:password="+request.getParameter("password"));
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        new ArrayList<>()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("successfulAuthentication");
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();

        response.addHeader("Authorization", JwtUtils.getTokenHeader(token));
    }
}
