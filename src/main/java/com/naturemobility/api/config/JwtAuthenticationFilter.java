package com.naturemobility.api.config;

import com.naturemobility.api.util.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenUtil jwtTokenUtil;

    /**
     * JwtTokenUtil 주입
     *
     * @param jwtTokenUtil
     */
    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Request로 들어오는 Jwt Token의 유효성을 검증하는 filter를 filter chain에 등록
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JWT: " + ((HttpServletRequest)request).getRequestURL());
        String token = jwtTokenUtil.resolveClientToken((HttpServletRequest) request);
        if (token != null && !jwtTokenUtil.isTokenExpired(token)) {
            Authentication auth = jwtTokenUtil.getClientAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
}
