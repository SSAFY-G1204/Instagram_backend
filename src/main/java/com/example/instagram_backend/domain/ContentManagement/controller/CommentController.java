package com.example.instagram_backend.domain.ContentManagement.controller;

import com.example.instagram_backend.domain.ContentManagement.dto.AddCommentRequestDto;
import com.example.instagram_backend.domain.ContentManagement.service.CommentService;
import com.example.instagram_backend.domain.response.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<ApiResponseEntity> addComment(@RequestBody AddCommentRequestDto addCommentRequestDto) {
        commentService.addComment(addCommentRequestDto.getPostId(), addCommentRequestDto);
        return ApiResponseEntity.toResponseEntity();
    }

}
