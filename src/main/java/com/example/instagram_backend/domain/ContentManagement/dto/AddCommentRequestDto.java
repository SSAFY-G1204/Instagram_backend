package com.example.instagram_backend.domain.ContentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCommentRequestDto {
    private Long userId; // 댓글 작성 사용자 ID
    private Long postId; // 댓글 달릴 게시물 ID
    private String content; // 댓글 내용
}