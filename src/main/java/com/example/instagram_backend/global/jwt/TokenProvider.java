package com.example.instagram_backend.global.jwt;


import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt){
        // 유저 객체와, 날짜를 생성해서
        Date now = new Date();
        return makeToken(new Date(now.getTime()+expiredAt.toMillis()), user);
    }

    public String makeToken(Date expiry, User user){
        Date now = new Date();
        //jwt Token을 생성했음.
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getUserId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //유효성 검사하기
    public boolean validToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) //비밀값으로 복호화하기
                    .parseClaimsJws(token);

            return true;
        }catch(Exception e){
            return false;
        }
    }

    //토큰 기반으로 인증 정보를 가져오는 메서드
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorites = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken
                (new org.springframework.security.core.userdetails.User(claims.getSubject(),"",authorites), token, authorites);
    }

    //4.토큰 기반으로 유저 id를 가져오는 메서드
    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("id",Long.class);
    }

    // 클레임 조회하는 메서드
    // parseClaimsJws(token)는 주어진 JWT 토큰을 파싱하여 위의 세 부분으로 분리한다. 또한 토큰의 서명을 검증하여 데이터의 무결성을 확인한다.
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
