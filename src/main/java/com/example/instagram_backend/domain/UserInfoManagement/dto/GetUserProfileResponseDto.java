package com.example.instagram_backend.domain.UserInfoManagement.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserProfileResponseDto {
    Long userId;
    String profileImg;
    String profileText;
    Integer followings;
    Integer followers;
}
