package com.example.instagram_backend.domain.ContentManagement.repository;

import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInteractions.dao.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 사용자가 해당 게시물에 중복으로 좋아요를 눌렀는지 확인해주기 위한 메서드
    boolean existsByUserAndPost(User user, Post post);
}
