package com.example.instagram_backend.domain.UserInteractions.service;

import com.example.instagram_backend.domain.UserInteractions.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void deleteLike(long likeId) { this.likeRepository.deleteById(likeId); }

    public boolean existsLike(long likeId) { return likeRepository.existsById(likeId); }

}
