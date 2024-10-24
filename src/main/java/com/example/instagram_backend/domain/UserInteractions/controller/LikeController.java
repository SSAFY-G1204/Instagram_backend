package com.example.instagram_backend.domain.UserInteractions.controller;

import com.example.instagram_backend.domain.UserInteractions.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable Long likeId) {
        // 존재하지 않은 like 라면 오류
        if (!likeService.existsLike(likeId)) {

        }
        likeService.deleteLike(likeId);
        return ResponseEntity.ok().build();
    }

}
