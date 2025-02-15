package com.issuemoa.users.presentation.filter;

import com.issuemoa.common.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final static String TOKEN_PREPIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);

        log.info("==> [TokenAuthenticationFilter] Token :: {}", token);

        // 토큰 유효 검증 && 인증 정보 설정
        if (tokenProvider.validToken(token)) {
            Authentication authentication = getAuthentication(token);
            log.info("==> [TokenAuthenticationFilter] authentication :: {}", authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String token) {
        Claims claims = tokenProvider.getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities)
                , token, authorities);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREPIX)) {
            return authorizationHeader.substring(TOKEN_PREPIX.length());
        }
        return null;
    }
}
