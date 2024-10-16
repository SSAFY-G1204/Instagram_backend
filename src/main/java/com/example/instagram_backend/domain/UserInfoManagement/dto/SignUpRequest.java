package com.example.instagram_backend.domain.UserInfoManagement.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequest {
    private String name;
    private String email;
    private String provider;
}
