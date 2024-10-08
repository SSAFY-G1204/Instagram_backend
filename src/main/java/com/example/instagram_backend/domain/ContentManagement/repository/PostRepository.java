package com.example.instagram_backend.domain.ContentManagement.repository;


import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.ContentManagement.dto.FeedResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserUserId(Long userId);



}
