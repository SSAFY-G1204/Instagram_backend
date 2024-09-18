package com.example.instagram_backend.domain.ContentManagement.controller;

import com.example.instagram_backend.domain.ContentManagement.dto.AddCommentRequestDto;
import com.example.instagram_backend.domain.ContentManagement.service.CommentService;
import com.example.instagram_backend.domain.ContentManagement.service.PostService;
import com.example.instagram_backend.domain.response.ApiResponseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    public void testAddComment() {
        AddCommentRequestDto addCommentRequestDto = new AddCommentRequestDto(1L, 1L, "Add Comment test");
        doNothing().when(commentService).addComment(addCommentRequestDto.getPostId(), addCommentRequestDto); // 컨트롤러 동작만 확인하게 가만히좀 있어봐
        ResponseEntity<ApiResponseEntity> response = commentController.addComment(addCommentRequestDto);
        assertEquals(ApiResponseEntity.toResponseEntity(), response);
        verify(commentService, times(1)).addComment(addCommentRequestDto.getPostId(), addCommentRequestDto);         // 호출 횟수 확인
    }
}