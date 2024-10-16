package com.example.instagram_backend.domain.UserInfoManagement.dao;


import jakarta.persistence.*;
import lombok.*;

@Table(name="refreshToken")
@NoArgsConstructor(access= AccessLevel.PROTECTED) //기본생성자
@Getter
@Entity
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Builder // 빌더 패턴
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    @Column(name = "refresh_token", columnDefinition = "LONGTEXT")
    String refreshToken;

    @Column
    private Long userId;


    //생성자
    public RefreshToken(Long userId, String refreshToken){
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    //update 메서드
    public RefreshToken update(String newRefreshToken){
        this.refreshToken = newRefreshToken;
        return  this;
    }




}
