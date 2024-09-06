package com.example.instagram_backend.domain.ContentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostRequestDto {
    private Long userId;
    private Long postId;
    private String content;
}
