package com.issuemoa.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.security.Key;
import java.time.Duration;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(Map<String, Object> user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(user, new Date(now.getTime() + expiredAt.toMillis()));
    }

    // JWT 토큰 생성
    private String makeToken(Map<String, Object> user, Date expiry) {
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 typ: JWT
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject((String) user.get("email"))
                .claim("id", user.get("id"))
                .claim("name", user.get("name"))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validToken(String token) {
        log.info("==> [TokenProvider] validToken :: {}", token);
        try {
            Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities)
                , token, authorities);
    }

    public Claims getClaims(String token) {
        try {
            // JWT 파싱
            return Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e) {
            log.error("[getClaims] MalformedJwtException Message : {}", e.getMessage());
        } catch (JwtException e) {
            log.error("[getClaims] JwtException Message : {}", e.getMessage());
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer"))
            return authorization.substring(7);
        return "";
    }

    public Map<String, Object> getUserInfo(String accessToken) {
        Claims claims = getClaims(accessToken);

        Map<String, Object> user = new HashMap<>();
        user.put("id", claims.get("id"));
        user.put("email", claims.getSubject());
        user.put("name", claims.get("name"));

        return user;
    }

    public Long getUserId(HttpServletRequest request) {
        String token = resolveToken(request);

        if (token.isEmpty()) return 0L;

        Claims claims = getClaims(token);
        return (Long) claims.get("id");
    }
}