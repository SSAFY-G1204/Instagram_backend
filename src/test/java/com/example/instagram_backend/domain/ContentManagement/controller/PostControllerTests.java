package com.example.instagram_backend.domain.ContentManagement.controller;

import com.example.instagram_backend.domain.ContentManagement.dto.AddLikeRequestDto;
import com.example.instagram_backend.domain.ContentManagement.dto.AddPostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.dto.UpdatePostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.service.PostService;
import com.example.instagram_backend.domain.response.ApiResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTests {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        // MockitoExtension handles initialization automatically
    }

    @Test
    public void testAddPost() {
        AddPostRequestDto postRequestDto = new AddPostRequestDto(1L, "content");
        doNothing().when(postService).addPost(postRequestDto);

        ResponseEntity<ApiResponseEntity> response = postController.addPost(postRequestDto);

        assertEquals(ApiResponseEntity.toResponseEntity(), response);
        verify(postService, times(1)).addPost(postRequestDto);
    }

    @Test
    public void testUpdatePost() {
        UpdatePostRequestDto postRequestDto = new UpdatePostRequestDto(1L, 1L, "updated content");
        doNothing().when(postService).updatePost(postRequestDto);

        ResponseEntity<ApiResponseEntity> response = postController.updatePost(postRequestDto);

        assertEquals(ApiResponseEntity.toResponseEntity(), response);
        verify(postService, times(1)).updatePost(postRequestDto);
    }

    @Test
    public void testAddLike() {
        AddLikeRequestDto addLikeRequestDto = new AddLikeRequestDto(1L, 1L);
        doNothing().when(postService).addLike(addLikeRequestDto);

        ResponseEntity<ApiResponseEntity> response = postController.addLike(addLikeRequestDto);

        assertEquals(ApiResponseEntity.toResponseEntity(), response);
        verify(postService, times(1)).addLike(addLikeRequestDto);
    }
}
