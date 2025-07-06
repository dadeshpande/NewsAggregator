package com.mynews.newsaggregator.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtility jwtUtil;

    public JwtAuthenticationFilter(JwtUtility jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                // Set authentication in the security context (custom implementation required)
                SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(username));
            }
        }
        chain.doFilter(request, response);
    }
}