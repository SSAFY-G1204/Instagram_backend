package com.example.instagram_backend.domain.ContentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostRequestDto {
    private Long userId;
    private String content;
}
