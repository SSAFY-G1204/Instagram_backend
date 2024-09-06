package com.example.instagram_backend.domain.ContentManagement.controller;

import com.example.instagram_backend.domain.ContentManagement.dto.AddPostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.dto.UpdatePostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.service.PostService;
import com.example.instagram_backend.domain.response.ApiResponseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTests {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
}