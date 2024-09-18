package com.example.instagram_backend.domain.ContentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddLikeRequestDto {
    private Long postId; // 게시물 ID
    private Long userId; // 사용자 ID
}
