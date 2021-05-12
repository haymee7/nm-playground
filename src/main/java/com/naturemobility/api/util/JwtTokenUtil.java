package com.naturemobility.api.util;

import com.naturemobility.api.dto.ClientDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT 생성 및 검증 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 1L;

//    public static final long JWT_ACCESS_TOKEN_VALIDITY = 1000 * 30;   // 30초만 토큰 유효
    public static final long JWT_ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60 * 5;   // 5시간만 토큰 유효
//    public static final long JWT_REFRESH_TOKEN_VALIDITY = 1000 * 60;   // 1분 토큰 유효
    public static final long JWT_REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 60;   // 60일 토큰 유효

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String resolveClientToken(HttpServletRequest request) {
        return request.getHeader("xClientToken");
    }

    /**
     * 토큰 만료 확인
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * username으로 토큰 생성
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    /**
     * 클라이언트로 refresh 토큰 생성(유효기간 2달)
     *
     * @param clientDto
     * @return
     */
    public String generateRefreshToken(ClientDto clientDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh_token");
        claims.put("contact", clientDto.getCsContact());
        return doGenerateRefreshToken(claims, clientDto.getService());
    }

    /**
     * Access token 생성(유효기간 5시간)
     *
     * @param claims
     * @param subject
     * @return
     */
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Refresh token 생성(유효기간 60일)
     *
     * @param claims
     * @param subject
     * @return
     */
    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * JWT 토큰으로 인증정보 조회(클라이언트 access token)
     *
     * @param token
     * @return
     */
    public Authentication getClientAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAllClaimsFromToken(token).get("pid").toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
