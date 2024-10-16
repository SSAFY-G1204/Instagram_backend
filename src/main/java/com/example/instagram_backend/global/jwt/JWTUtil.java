package com.example.instagram_backend.global.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey secretKey;
    //Auth time 20분 설정하기
    private static final long AUTH_TIME = 20*60;
    private static final long REFRESH_TIME = 7*24*60*60;
    //application.properties에 있는 secretkey 가져오는 것
    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
        //암호화 방식
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    // 아래 메서드들은 당연하게도 인가를 성공했을 때 CustomSuccessHandler에서 작동된다. (유효성 검사)
    // 토큰에서 username 추출 메소드
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token)     // JWT 파싱
                .getBody()                 // 클레임 바디 가져오기
                .get("username", String.class);  // "username" 필드 추출
    }

    // 토큰에서 role 추출 메소드
    public String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token)     // JWT 파싱
                .getBody()                 // 클레임 바디 가져오기
                .get("role", String.class);  // "role" 필드 추출
    }

    // 토큰 만료 여부 확인 메소드
    public Boolean isExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(token)     // JWT 파싱
                .getBody()                 // 클레임 바디 가져오기
                .getExpiration()           // 만료 시간 추출
                .before(new Date());       // 현재 시간과 비교
    }

    // Access Token 생성 메소드
    public String createJwt(String username, String role) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // 헤더 설정
                .claim("username", username)                   // username 클레임 추가
                .claim("role", role)                           // role 클레임 추가
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + AUTH_TIME))  // 만료 시간
                .signWith(secretKey)                           // 서명 키 설정
                .compact();                                    // 토큰 생성
    }

    // Refresh Token 생성 메소드
    public String createRefreshJwt(String username) {
        return Jwts.builder()
                .claim("username", username)                   // username 클레임 추가
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TIME))  // 만료 시간
                .signWith(secretKey)                           // 서명 키 설정
                .compact();                                    // 토큰 생성
    }

}