package com.zhuzichu.blog.filter;

import com.zhuzichu.blog.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        logger.info("注册了");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        String header = request.getHeader("Authorization");
        logger.info("doFilterInternal执行了:"+header);

        if (header == null || !header.startsWith(JwtUtils.getAuthorizationHeaderPrefix())) {
            chain.doFilter(request, response);
            logger.info(" chain.doFilter(request, response)");
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        String user = Jwts.parser()
                .setSigningKey("PrivateSecret")
                .parseClaimsJws(token.replace(JwtUtils.getAuthorizationHeaderPrefix(), ""))
                .getBody()
                .getSubject();
        logger.info("---------------------:"+user);
        if (null != user) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }

        return null;
    }
}
