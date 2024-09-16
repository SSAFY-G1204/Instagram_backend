package com.example.instagram_backend.domain.UserInfoManagement.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserProfileRequestDto {
    String profileImg;
    String profileText;
}
