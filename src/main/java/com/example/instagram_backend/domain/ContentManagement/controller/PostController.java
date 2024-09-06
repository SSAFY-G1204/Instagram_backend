package com.example.instagram_backend.domain.ContentManagement.controller;

import com.example.instagram_backend.domain.ContentManagement.dto.AddPostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.dto.FeedResponseDto;
import com.example.instagram_backend.domain.ContentManagement.dto.UpdatePostRequestDto;
import com.example.instagram_backend.domain.ContentManagement.service.PostService;
import com.example.instagram_backend.domain.response.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {


    @Autowired
    private PostService postService;


    //내가 팔로잉 하는 사람의 post 객체 찾기
    @GetMapping("/home/feeds")
    public List<FeedResponseDto> GetMyFeed(@RequestParam long userId){
        return postService.findFolollowingPostsByUserId(userId);
    }

//    @GetMapping("/home/test")
//    public List<testDto> GetMyTest(){
//        return postService.findPost();
//    }
//
    @PostMapping("/posts")
    public ResponseEntity<ApiResponseEntity> addPost(@RequestBody AddPostRequestDto postRequestDto) {
        postService.addPost(postRequestDto);
        return ApiResponseEntity.toResponseEntity();

    }

    @PutMapping("/posts")
    public ResponseEntity<ApiResponseEntity> updatePost(@RequestBody UpdatePostRequestDto postRequestDto) {
        postService.updatePost(postRequestDto);
        return ApiResponseEntity.toResponseEntity();
    }
}
